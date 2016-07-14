package com.saat.auto.cafe.common.models;

public enum ExampleType {
    SIMPLE_EXAMPLE,
    COMPLEX_EXAMPLE;

    /**
     * This method allows us to serialize the enum values as lower case in JSON objects.
     * (See the settings that make this work in the application.properties file in the api module.)
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
