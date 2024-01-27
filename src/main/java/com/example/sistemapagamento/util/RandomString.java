package com.example.sistemapagamento.util;

import java.security.SecureRandom;

public class RandomString {

    private static final String CARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateRandomString(int length) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(CARACTERS.length());
            stringBuilder.append(CARACTERS.charAt(index));
        }

        return stringBuilder.toString();
    }
}
