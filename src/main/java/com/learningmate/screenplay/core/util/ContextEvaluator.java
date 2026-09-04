package com.learningmate.screenplay.core.util;

import net.serenitybdd.screenplay.Actor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Context engine for resolving dynamic expressions and generating test data.
 * Supports placeholders like ${gen:email}, ${gen:firstname}, ${gen:uuid}, etc.
 * Generated values are automatically stored in the actor's memory for reuse.
 */
public class ContextEvaluator {

    private static final String PLACEHOLDER_PATTERN = "\\$\\{([^}]+)\\}";
    private static final Pattern PATTERN = Pattern.compile(PLACEHOLDER_PATTERN);
    private static final String MEMORY_KEY_PREFIX = "generated_";

    /**
     * Evaluates a string containing dynamic expressions and returns the resolved value.
     * Generated values are stored in actor memory for reference in subsequent steps.
     * Examples:
     * - "${gen:email}" -> "user_<uuid>@example.com"
     * - "${gen:firstname}" -> "User_<uuid>"
     * - "${gen:uuid}" -> "<random-uuid>"
     *
     * @param actor the Screenplay actor (used to store generated values in memory)
     * @param input the input string potentially containing placeholders
     * @return the resolved string with placeholders replaced
     */
    public static String evaluate(Actor actor, String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuffer result = new StringBuffer();
        Matcher matcher = PATTERN.matcher(input);

        while (matcher.find()) {
            String placeholder = matcher.group(1);
            String replacement = resolveExpression(actor, placeholder);
            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(result);

        return result.toString();
    }

    /**
     * Evaluates a map of key-value pairs, resolving all dynamic expressions.
     * Useful for populating forms with generated test data.
     *
     * @param actor the Screenplay actor
     * @param data a map of field names to values (potentially containing placeholders)
     * @return a new map with all placeholders resolved
     */
    public static Map<String, String> evaluateMap(Actor actor, Map<String, String> data) {
        Map<String, String> evaluatedData = new HashMap<>();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            evaluatedData.put(entry.getKey(), evaluate(actor, entry.getValue()));
        }
        return evaluatedData;
    }

    private static String resolveExpression(Actor actor, String expression) {
        String[] parts = expression.split(":", 2);
        String type = parts[0].trim();
        String memoryKey = MEMORY_KEY_PREFIX + expression;

        // Check if value was already generated and stored
        if (actor.recall(memoryKey) != null) {
            return actor.recall(memoryKey);
        }

        String generated = switch (type.toLowerCase()) {
            case "gen" -> generateValue(parts.length > 1 ? parts[1].trim() : "uuid");
            default -> "${" + expression + "}"; // Return unchanged if unrecognized
        };

        // Store for future reference
        actor.remember(memoryKey, generated);
        return generated;
    }

    private static String generateValue(String generatorType) {
        return switch (generatorType.toLowerCase()) {
            case "email" -> "user_" + generateUUID() + "@example.com";
            case "firstname" -> "User_" + generateUUID();
            case "lastname" -> "TestUser_" + generateUUID();
            case "username" -> "testuser_" + generateUUID();
            case "uuid" -> generateUUID();
            default -> "${gen:" + generatorType + "}";
        };
    }

    private static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }
}
