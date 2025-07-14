package utils;

import javax.crypto.SecretKey;

public class AESGCMTest {
    public static void main(String[] args) throws Exception {
        SecretKey key = AESGCMUtil.generateKey();

        String originalText = "Encrypt this secret message!";
        String encrypted = AESGCMUtil.encrypt(originalText, key);
        String decrypted = AESGCMUtil.decrypt(encrypted, key);

        System.out.println("🔐 Encrypted: " + encrypted);
        System.out.println("📤 Decrypted: " + decrypted);
    }
}
