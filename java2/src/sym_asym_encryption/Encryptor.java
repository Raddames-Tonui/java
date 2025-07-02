package sym_asym_encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.*;
import java.util.Base64;

public class Encryptor {
    public static void main(String[] args) throws Exception {

        // 1. Generate AES symmetric key (Used for data Encryption)
        KeyGenerator aesKeyGen = KeyGenerator.getInstance("AES");
        aesKeyGen.init(128);
        SecretKey aesKey = aesKeyGen.generateKey();

        // 2. Generate RSA key pair (public and private)
        KeyPairGenerator rsaKeyGen = KeyPairGenerator.getInstance("RSA");
        rsaKeyGen.initialize(2048);
        KeyPair rsaKey = rsaKeyGen.generateKeyPair();
        PublicKey publicKey = rsaKey.getPublic();
        PrivateKey privateKey = rsaKey.getPrivate();

        // 3. Encrypt AES key with RSA public Key
        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedAESKey = rsaCipher.doFinal(aesKey.getEncoded());

        // 4. Encrypt sample XML using AES
        String xml = "<data><user>Admin</user><access>Full</access></data>";
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // AES requires an IV (Initial Vector)
        byte[] iv =new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey, ivSpec);
        byte[] encryptedXML = aesCipher.doFinal(xml.getBytes());

        // 5. Sign the encrypted XML using RSA private Key
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(encryptedXML);
        byte[] digitalSignature = signature.sign();

        // 6. Display the results
        System.out.println("🔐 Encrypted AES Key: " + Base64.getEncoder().encodeToString(encryptedAESKey));
        System.out.println("📦 Encrypted XML: " + Base64.getEncoder().encodeToString(encryptedXML));
        System.out.println("🧬 IV: " + Base64.getEncoder().encodeToString(iv));
        System.out.println("✍️ Signature: " + Base64.getEncoder().encodeToString(digitalSignature));
        System.out.println("📤 Public Key: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        System.out.println("🔐 Private Key: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));


    }
}
