import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class AESGCMEncryption {

    // === CONSTANTS FOR AES-GCM ===

    // GCM IV (Initialization Vector) size should be 12 bytes (96 bits)
    private static final int IV_SIZE = 12;

    // GCM Authentication Tag is typically 128 bits (16 bytes)
    private static final int TAG_BIT_LENGTH = 128;

    // AES-128 requires a key of 128 bits (16 bytes)
    private static final int AES_KEY_SIZE = 128;

    public static void main(String[] args) throws Exception {

        // === STEP 1: Generate AES-128 Secret Key ===
        // AES is a symmetric cipher, so both encryption and decryption use the same key.
        // KeyGenerator generates a fresh, random 128-bit AES key for secure encryption.
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(AES_KEY_SIZE); // AES-128 (128 bits = 16 bytes)
        SecretKey aesKey = keyGen.generateKey();
        // 🔐 This key is used in Step 4 for encryption, and again in Step 8 for decryption.

        // === STEP 2: Define the message to be encrypted ===
        // This is the plain text input we want to secure. In practice, it might be a password,
        // user information, financial data, etc.
        String plaintext = "Confidential Message for AES-GCM Encryption";

        // === STEP 3: Generate a secure random Initialization Vector (IV) ===
        // GCM mode requires a unique 12-byte IV for each encryption.
        // It ensures semantic security (prevents identical plaintexts from producing same ciphertext).
        byte[] iv = new byte[IV_SIZE];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);
        System.out.println("Raw IV: " + Arrays.toString(iv));
        // 🛡️ IV is not secret, but MUST be unique per encryption or you lose security guarantees.

        // === STEP 4: Initialize Cipher in ENCRYPT_MODE using AES/GCM/NoPadding ===
        // AES/GCM/NoPadding means AES in GCM mode with no manual padding required.
        // GCM mode combines both encryption and authentication (AEAD).
        Cipher encryptCipher = Cipher.getInstance("AES/GCM/NoPadding");

        // GCMParameterSpec defines the tag length and IV. Tag ensures message authenticity.
        GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_BIT_LENGTH, iv);
        encryptCipher.init(Cipher.ENCRYPT_MODE, aesKey, gcmSpec);

        // === STEP 5: Encrypt the message ===
        // The input plaintext is converted to UTF-8 bytes and passed into the cipher.
        byte[] ciphertext = encryptCipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        // 📦 The result contains encrypted data + GCM authentication tag appended at the end.
        System.out.println("Raw Cipher: " + Arrays.toString(ciphertext));

        // === STEP 6: Concatenate IV + Ciphertext ===
        // The IV is needed for decryption, so it's typically prefixed to the encrypted payload.
        byte[] encryptedMessage = new byte[iv.length + ciphertext.length];
        System.arraycopy(iv, 0, encryptedMessage, 0, iv.length);
        System.arraycopy(ciphertext, 0, encryptedMessage, iv.length, ciphertext.length);

        // Encode to Base64 for easy transmission/storage (logs, JSON, etc.)
        System.out.println("🔐 Encrypted (Base64): " + Base64.getEncoder().encodeToString(encryptedMessage));

        // ==================== DECRYPTION SECTION ====================

        // === STEP 7: Decode and extract IV and actual ciphertext ===
        // In real scenarios, the message might arrive as a Base64 string from the network or file.
        byte[] decodedMessage = encryptedMessage;  // Already have the raw bytes

        // Split out the IV (first 12 bytes) and ciphertext+tag
        byte[] extractedIV = new byte[IV_SIZE];
        byte[] actualCiphertext = new byte[decodedMessage.length - IV_SIZE];
        System.arraycopy(decodedMessage, 0, extractedIV, 0, IV_SIZE);
        System.arraycopy(decodedMessage, IV_SIZE, actualCiphertext, 0, actualCiphertext.length);

        // === STEP 8: Initialize Cipher in DECRYPT_MODE with same key and extracted IV ===
        // GCMParameterSpec must match the one used in encryption: same IV, same tag size.
        Cipher decryptCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec decryptSpec = new GCMParameterSpec(TAG_BIT_LENGTH, extractedIV);
        decryptCipher.init(Cipher.DECRYPT_MODE, aesKey, decryptSpec);

        // === STEP 9: Decrypt the ciphertext ===
        // doFinal() also checks the GCM tag to ensure the data wasn't tampered with.
        byte[] decryptedBytes = decryptCipher.doFinal(actualCiphertext);
        String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);

        // Final Output: Print the original message
        System.out.println("📤 Decrypted Text: " + decryptedText);
    }
}
