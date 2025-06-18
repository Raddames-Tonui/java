# Asymmetric Encryption & Digital Signatures in Java

## 📖 What Is Asymmetric Encryption?

Asymmetric encryption, also known as public-key cryptography, is a cryptographic system that uses two separate keys:

* A **public key**: Used for encryption and signature verification.
* A **private key**: Used for decryption and signing.

### 🗝️ Super Simple Rule:

    Encrypt = use Public key
    Decrypt = use Private key
    Sign = use Private key
    Verify = use Public key

This allows secure communication and digital verification without having to share private keys.

## ✍️ What Are Digital Signatures?

Digital signatures verify the authenticity and integrity of a message:

* The message is hashed.
* The hash is encrypted with the sender’s **private key** (generating the signature).
* The recipient decrypts the signature using the **public key** and compares it with the message's hash.

If they match, the message is verified. If altered, the verification fails.

---

## 🌍 Where Is Asymmetric Encryption Used?

Asymmetric encryption plays a critical role in modern security infrastructure:

* **HTTPS (SSL/TLS)**: Used to exchange encryption keys securely between client and server.
* **Email Security**: S/MIME and PGP use asymmetric encryption for signing and encrypting emails.
* **Digital Signatures**: Used in document verification, code signing, and blockchain transactions.
* **Cryptocurrencies**: Bitcoin and Ethereum wallets use asymmetric cryptography for transaction signing.
* **Software Licensing**: Securely verify license keys using public/private key mechanisms.
* **Secure Shell (SSH)**: Authenticates users and encrypts session traffic.
* **JWT Tokens**: Signed with private keys and verified using public keys in RS256.

---

## 🌳 Java Security Class Tree (Simplified and Explained)

```
java.lang.Object
 ├── java.security.Key (interface)
 │    ├── PublicKey (interface)
 │    └── PrivateKey (interface)
 │
 ├── java.security.KeyPair
 │    └── getPublic(), getPrivate()
 │
 ├── java.security.KeyPairGenerator
 │    └── initialize(int keySize)
 │    └── generateKeyPair()
 │
 ├── java.security.Signature
 │    └── getInstance("SHA256withRSA")
 │    └── initSign(PrivateKey), initVerify(PublicKey)
 │    └── update(byte[]), sign(), verify(byte[])
 │
 └── javax.crypto.Cipher
      └── getInstance("RSA")
      └── init(ENCRYPT_MODE | DECRYPT_MODE, Key)
      └── doFinal(byte[])
```

---

## 🔧 Core Methods & What They Do

### KeyPairGenerator

* `getInstance("RSA")`: Returns a generator for RSA key pairs.
* `initialize(2048)`: Sets key size to 2048 bits.
* `generateKeyPair()`: Creates a new public/private key pair.

### KeyPair

* `getPublic()`: Returns the public key.
* `getPrivate()`: Returns the private key.

### Cipher

* `getInstance("RSA")`: Returns an RSA cipher.
* `init(mode, key)`: Prepares the cipher for encryption or decryption.
* `doFinal(data)`: Encrypts or decrypts the byte array.

### Signature

* `getInstance("SHA256withRSA")`: Creates a signature object using SHA-256 hash and RSA.
* `initSign(privateKey)`: Prepares the object for signing.
* `sign()`: Generates the signature.
* `initVerify(publicKey)`: Prepares for verification.
* `verify(signature)`: Verifies a signature.

---

## 💻 Example Code With Comments

```java
import java.security.*;
import java.util.Base64;
import javax.crypto.Cipher;

public class RSADemo {

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    public static String encrypt(String plaintext, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(plaintext.getBytes()));
    }

    public static String decrypt(String encryptedText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedText)));
    }

    public static String sign(String message, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    public static boolean verify(String message, String signatureStr, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(message.getBytes());
        return signature.verify(Base64.getDecoder().decode(signatureStr));
    }

    public static void main(String[] args) throws Exception {
        String message = "Hello, secure world!";

        // Key generation
        KeyPair keyPair = generateKeyPair();

        // Encrypt and Decrypt
        String encrypted = encrypt(message, keyPair.getPublic());
        String decrypted = decrypt(encrypted, keyPair.getPrivate());

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        // Sign and Verify
        String signature = sign(message, keyPair.getPrivate());
        boolean isVerified = verify(message, signature, keyPair.getPublic());

        System.out.println("Signature: " + signature);
        System.out.println("Verified: " + isVerified);

        // Tamper test
        boolean tampered = verify("Hello, hacked world!", signature, keyPair.getPublic());
        System.out.println("Tampered Verification: " + tampered);
    }
}
```

---

## 🧠 Related Topics To Explore

* **Elliptic Curve Cryptography (ECC)**
* **Hybrid Cryptography (AES + RSA)**
* **KeyStore in Java for storing keys**
* **Java's `java.security.cert` for certificates**
* **HTTPS and TLS internals using asymmetric encryption**

---

## ✅ Summary

This README serves as a complete guide for understanding and implementing RSA-based asymmetric encryption and digital signatures in Java. You now have the foundational knowledge to secure data, verify integrity, and build real-world applications that require secure communications.
