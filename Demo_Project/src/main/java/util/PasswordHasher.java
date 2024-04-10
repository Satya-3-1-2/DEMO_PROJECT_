package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHasher {

    private static final int SALT_LENGTH = 16;

    public static String hashPassword(String password) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[SALT_LENGTH];
            random.nextBytes(salt);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);

            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        try {
            byte[] hashedPasswordBytes = Base64.getDecoder().decode(hashedPassword);

            byte[] salt = new byte[SALT_LENGTH];
            System.arraycopy(hashedPasswordBytes, 0, salt, 0, SALT_LENGTH);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);

            byte[] hashedInputPassword = md.digest(password.getBytes());

            for (int i = 0; i < SALT_LENGTH; i++) {
                if (hashedPasswordBytes[i] != salt[i]) {
                    return false;
                }
            }

            for (int i = SALT_LENGTH; i < hashedPasswordBytes.length; i++) {
                if (hashedPasswordBytes[i] != hashedInputPassword[i - SALT_LENGTH]) {
                    return false;
                }
            }

            return true;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }
}
