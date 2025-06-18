import AsymmetricEncryptionDemo;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Main{
    /**
         * Main method: Demonstrates all operations and tampering detection.
         */
    public static void main(String[] args) throws Exception {
            String message = "Confidential: Your OTP is 123456.";

            // Step 1: Generate RSA key pair
            KeyPair keyPair = generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Step 2: Encrypt and Decrypt
            String encrypted = encrypt(message, publicKey);
            String decrypted = decrypt(encrypted, privateKey);

            System.out.println("Original Message: " + message);
            System.out.println("Encrypted Message: " + encrypted);
            System.out.println("Decrypted Message: " + decrypted);

            // Step 3: Sign and Verify
            String digitalSignature = sign(message, privateKey);
            boolean isVerified = verify(message, digitalSignature, publicKey);

            System.out.println("Digital Signature: " + digitalSignature);
            System.out.println("Signature Verified: " + isVerified);

            // Step 4: Tampering Detection
            String tamperedMessage = "Confidential: Your OTP is 999999.";
            boolean tamperedVerified = verify(tamperedMessage, digitalSignature, publicKey);

            System.out.println("Tampered Message: " + tamperedMessage);
            System.out.println("Tampered Signature Verified: " + tamperedVerified);
    }
}
