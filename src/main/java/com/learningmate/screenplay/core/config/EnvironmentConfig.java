package com.learningmate.screenplay.core.config;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

public class EnvironmentConfig {

    private static final EnvironmentVariables ENVIRONMENT_VARIABLES = SystemEnvironmentVariables.createEnvironmentVariables();

    public static String getProperty(String propertyName) {
        return EnvironmentSpecificConfiguration.from(ENVIRONMENT_VARIABLES)
                .getProperty(propertyName);
    }

    public static String getMoodleApiToken() {
        return getProperty("moodle.api.token");
    }

    public static String getMoodleBaseUrl() {
        return getProperty("moodle.base.url");
    }
}