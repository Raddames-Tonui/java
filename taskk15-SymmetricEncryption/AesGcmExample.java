import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AesGcmExample {

    // Constants
    private static final int AES_KEY_SIZE = 128; // bits
    private static final int GCM_IV_LENGTH = 12; // bytes (recommended)
    private static final int GCM_TAG_LENGTH = 128; // bits

    public static void main(String[] args) throws Exception {
        String plainText = "Raddames";

        // Step 1: Generate AES-128 key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(AES_KEY_SIZE);
        SecretKey key = keyGen.generateKey(); // Symmetric key

        // Step 2: Generate IV (Initialization Vector)
        byte[] iv = new byte[GCM_IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv); // Secure random Initialization Vector

        // Step 3: Encrypt
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding"); // Mode of operation
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv); // Auth tag length and IV
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] cipherText = cipher.doFinal(plainText.getBytes());

        // Step 4: Encode cipherText and IV to send/store
        String encryptedData = Base64.getEncoder().encodeToString(cipherText);
        String encodedIV = Base64.getEncoder().encodeToString(iv);
        System.out.println("Encrypted: " + encryptedData);
        System.out.println("IV: " + encodedIV);

        // Step 5: Decrypt
        Cipher decipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec decSpec = new GCMParameterSpec(GCM_TAG_LENGTH, Base64.getDecoder().decode(encodedIV));
        decipher.init(Cipher.DECRYPT_MODE, key, decSpec);
        byte[] decryptedBytes = decipher.doFinal(Base64.getDecoder().decode(encryptedData));

        String decryptedText = new String(decryptedBytes);
        System.out.println("Decrypted: " + decryptedText);
    }
}
