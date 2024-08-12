package dev.flab.studytogether.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class RandomUtil {
    private static SecureRandom secureRandom;

    static {
        try {
            secureRandom = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private RandomUtil() {} // 인스턴스화 방지

    public static String generateRandomToken(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder token = new StringBuilder(length);

        for(int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(characters.length());
            token.append(characters.charAt(index));
        }

        return token.toString();
    }
}
