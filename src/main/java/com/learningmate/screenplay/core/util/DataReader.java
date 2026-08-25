package com.learningmate.screenplay.core.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class DataReader {

    private static final Map<String, JsonNode> loadedFiles = new HashMap<>();
    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());


    public static Map<String, String> getRecord(String resourcePath, String keyPath) {
        try {
            JsonNode rootNode = loadedFiles.computeIfAbsent(resourcePath, path -> {
                try (InputStream input = DataReader.class.getClassLoader().getResourceAsStream(path)) {
                    if (input == null) {
                        throw new RuntimeException("YAML file not found: " + path);
                    }
                    return mapper.readTree(input);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to read YAML: " + path, e);
                }
            });

            String[] keys = keyPath.split("\\.");
            JsonNode current = rootNode;
            for (String key : keys) {
                current = current.get(key);
                if (current == null) {
                    throw new IllegalArgumentException("Key path not found: " + keyPath + " in " + resourcePath);
                }
            }

            Map<String, String> resultMap = new HashMap<>();
            current.fields().forEachRemaining(entry -> resultMap.put(entry.getKey(), entry.getValue().asText()));
            return resultMap;
        } catch (Exception e) {
            throw new RuntimeException("Error reading data map from " + resourcePath, e);
        }
    }





    public static String get(String resourcePath, String keyPath) {
        try {
            JsonNode rootNode = loadedFiles.computeIfAbsent(resourcePath, path -> {
                try (InputStream input = DataReader.class.getClassLoader().getResourceAsStream(path)) {
                    if (input == null) {
                        throw new RuntimeException("YAML file not found: " + path);
                    }
                    return mapper.readTree(input);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to read YAML: " + path, e);
                }
            });

            String[] keys = keyPath.split("\\.");
            JsonNode current = rootNode;
            for (String key : keys) {
                current = current.get(key);
                if (current == null) {
                    throw new IllegalArgumentException("Key path not found: " + keyPath + " in " + resourcePath);
                }
            }
            return current.asText();
        } catch (Exception e) {
            throw new RuntimeException("Error reading data from " + resourcePath, e);
        }
    }
}