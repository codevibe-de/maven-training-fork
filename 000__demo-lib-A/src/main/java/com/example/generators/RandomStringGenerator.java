package com.example.generators;

import java.util.Random;

public final class RandomStringGenerator {

    private RandomStringGenerator() {
    }

    public static String generateRandomString(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be at least 1");
        }
        StringBuilder result = new StringBuilder();
        Random r = new Random();
        for (int n = 0; n < length; n++) {
            char c = (char) (r.nextInt(26) + 'a');
            result.append(c);
        }
        return result.toString();
    }
}
