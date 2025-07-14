package utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class AESGCMUtil {

    // Constants for GCM
    private static final int AES_KEY_SIZE = 128;        // 128-bit AES key
    private static final int IV_SIZE = 12;              // 12 bytes = 96 bits, GCM standard
    private static final int TAG_BIT_LENGTH = 128;      // 16 bytes tag for authentication

    /**
     * Generates a new 128-bit AES key for GCM encryption.
     */
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(AES_KEY_SIZE);
        return keyGen.generateKey();
    }

    /**
     * Encrypts plaintext using AES-GCM.
     * @param plaintext Plain text to encrypt
     * @param key SecretKey (AES-128)
     * @return Base64 encoded IV + ciphertext + tag
     */
    public static String encrypt(String plaintext, SecretKey key) throws Exception {
        byte[] iv = new byte[IV_SIZE];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv); // Secure, random IV for each encryption

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_BIT_LENGTH, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, gcmSpec);

        byte[] ciphertext = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

        // Prepend IV to ciphertext before Base64 encoding
        byte[] encrypted = new byte[iv.length + ciphertext.length];
        System.arraycopy(iv, 0, encrypted, 0, iv.length);
        System.arraycopy(ciphertext, 0, encrypted, iv.length, ciphertext.length);

        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     * Decrypts AES-GCM encrypted Base64 string.
     * @param base64CipherText Encrypted text (Base64 encoded IV + cipher + tag)
     * @param key SecretKey used during encryption
     * @return Original plaintext
     */
    public static String decrypt(String base64CipherText, SecretKey key) throws Exception {
        byte[] encrypted = Base64.getDecoder().decode(base64CipherText);

        // Extract IV and ciphertext
        byte[] iv = new byte[IV_SIZE];
        byte[] ciphertext = new byte[encrypted.length - IV_SIZE];

        System.arraycopy(encrypted, 0, iv, 0, IV_SIZE);
        System.arraycopy(encrypted, IV_SIZE, ciphertext, 0, ciphertext.length);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_BIT_LENGTH, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, gcmSpec);

        byte[] decrypted = cipher.doFinal(ciphertext);
        return new String(decrypted, StandardCharsets.UTF_8);
    }
}
