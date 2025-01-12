package ru.khtu.statemachine.app.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

class ResourceHelper {

    public static String builderClassLoad (String testResourceName) throws IOException {
        try (InputStream inputStream = StatemachineServiceImplTest.class.getClassLoader().getResourceAsStream(testResourceName);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8) ) ) {
            return bufferedReader
                    .lines()
                    .collect(Collectors.joining(""));
        }
    }

    public static String testClassLoad (String testResourceName) throws IOException {
        try (InputStream inputStream = StatemachineServiceImplTest.class.getClassLoader().getResourceAsStream(testResourceName);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8) ) ) {
            return bufferedReader
                    .lines()
                    .collect(Collectors.joining(""));
        }
    }

}
