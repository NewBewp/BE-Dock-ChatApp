package com.example.springbase;

import org.apache.commons.lang3.RandomStringUtils;

public class Test {
    public static String generateRandomString() {
        return "JWT=KEY=" + RandomStringUtils.randomAlphabetic(70)+ "=END";
    }

    public static void main(String[] args) {
        String randomString = generateRandomString();
        System.out.println("Random String: " + randomString);
    }
}
