package com.example.parautomini.Entites;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;

class LicenseTest {

    @Test
    void test() {
        String s1 = "Hello";
        String s2 = "World";
        String s3 = "JUnit";
        assertAll("Test multiple assertions",
                () -> assertEquals("Hello", s1),
                () -> assertEquals("World", s2),
                () -> assertEquals("JUnit", s3)
        );
    }
}