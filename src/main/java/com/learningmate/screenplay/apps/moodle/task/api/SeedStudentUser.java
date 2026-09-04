package com.learningmate.screenplay.apps.moodle.task.api;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

import java.util.Locale;
import java.util.Map;




public class SeedStudentUser implements Task {

    public static final String TASK_CLASS_NAME = "SeedStudentUser";
    private static final String ENDPOINT_PATH = "/webservice/rest/server.php";
    private static final String DEFAULT_BASE_URL = "https://sandbox.moodledemo.net";
    private static final String DEFAULT_AUTH_TOKEN = "";
    private static final String WS_FUNCTION = "core_user_create_users";
    private static final String RESPONSE_FORMAT = "json";
    private static final String AUTH_VALUE = "manual";

    private final Map<String, String> studentData;

    public SeedStudentUser(Map<String, String> studentData) {
        this.studentData = studentData;
    }

    public static SeedStudentUser withDetails(Map<String, String> studentData) {
        return Tasks.instrumented(SeedStudentUser.class, studentData);
    }

    @Override
    @Step("{0} executes API task " + TASK_CLASS_NAME)
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.given()
                .baseUri(configValue("moodle.base.url", DEFAULT_BASE_URL))
                .basePath(ENDPOINT_PATH)
                .contentType("application/x-www-form-urlencoded")
                .formParam("wstoken", configValue("moodle.api.token", DEFAULT_AUTH_TOKEN))
                .formParam("wsfunction", WS_FUNCTION)
                .formParam("moodlewsrestformat", RESPONSE_FORMAT)
                .formParam("users[0][username]", requiredValue("username"))
                .formParam("users[0][password]", requiredValue("password"))
                .formParam("users[0][firstname]", requiredValue("firstname"))
                .formParam("users[0][lastname]", requiredValue("lastname"))
                .formParam("users[0][email]", requiredValue("email"))
                .formParam("users[0][auth]", "manual")
                .formParam("users[0][preferences][0][type]", "auth_forcepasswordchange")
                .formParam("users[0][preferences][0][value]", "0")
                .when()
                .post()
                .then()
                .statusCode(200);
    }
    private String requiredValue(String key) {
        String value = studentData.get(key);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Missing required student data field: " + key);
        }
        return value;
    }

    private String configValue(String key, String defaultValue) {
        String value = System.getProperty(key);

        if (isBlank(value)) {
            String envKey = key.toUpperCase(Locale.ROOT).replace('.', '_');
            value = System.getenv(envKey);
        }

        if (isBlank(value)) {
            EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
            value = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(key);
        }

        return isBlank(value) ? defaultValue : value;
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
