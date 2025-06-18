import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Arrays;


public class AESGCMEncryption {

    // Constants
    private static final int AES_KEY_SIZE = 128; // in bits
    private static final int IV_SIZE = 12;       // GCM recommended 96 bits = 12 bytes
    private static final int TAG_LENGTH = 128;   // Authentication tag length (in bits)

    public static void main(String[] args) throws Exception {

        // 1. Generate AES 128-bit Key
        byte[] keyBytes = new byte[16]; // 16 bytes = 128 bits
        new SecureRandom().nextBytes(keyBytes);
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");  

        // 2. Generate 12-byte IV (Initialization Vector)
        byte[] iv = new byte[IV_SIZE];
        new SecureRandom().nextBytes(iv);
        GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_LENGTH, iv);

        System.out.println("Raw IV: " + Arrays.toString(iv));


        // 3. Message to Encrypt
        String plainText = "Confidential message for Task 15.";

        // 4. Encrypt
        Cipher encryptCipher = Cipher.getInstance("AES/GCM/NoPadding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmSpec);
        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes());

        System.out.println("Raw Cipher: " + Arrays.toString(cipherText));

        // 5. Encode output to Base64 (for readability and transmission)
        String encryptedBase64 = Base64.getEncoder().encodeToString(cipherText);
        String ivBase64 = Base64.getEncoder().encodeToString(iv);

        System.out.println("Encrypted Cipher (Base64): " + encryptedBase64);
        System.out.println("Encrypted IV (Base64): " + ivBase64);

        // 6. Decrypt (Using same key + IV)
        GCMParameterSpec decryptGcmSpec = new GCMParameterSpec(TAG_LENGTH, Base64.getDecoder().decode(ivBase64));
        Cipher decryptCipher = Cipher.getInstance("AES/GCM/NoPadding");
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, decryptGcmSpec);
        byte[] decryptedBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedBase64));

        String decryptedText = new String(decryptedBytes);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
