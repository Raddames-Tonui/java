# Java Cryptography Overview

This README provides an in-depth explanation of Java's cryptographic classes, their functions, and typical usage. It covers both symmetric and asymmetric encryption, key management, and cryptographic utilities within the Java Cryptography Architecture (JCA).

---

## 1. Symmetric Encryption (Secret-Key Cryptography)

### Key Interfaces and Classes

* **Key** (interface): The root interface for all keys.
* **SecretKey** (interface): A key used in symmetric algorithms.
* **SecretKeySpec** (class): A concrete implementation of `SecretKey` used to construct keys from byte arrays.

### Cipher Classes

* **Cipher** (final class): The primary class for encryption and decryption.
* **CipherSpi** (abstract class): Defines the Service Provider Interface (SPI) for Cipher implementations. Not used directly.

### Parameter Specifications

* **AlgorithmParameterSpec** (interface): Base interface for algorithm parameters.
* **IvParameterSpec** (class): Used to specify an initialization vector (IV) for block ciphers like AES/CBC.
* **GCMParameterSpec** (class): Used with AES/GCM, contains IV and authentication tag length.

### Related Utilities

* **KeyGenerator**: Generates secret keys for symmetric algorithms like AES.

### Class Hierarchy Tree

```
java.lang.Object
 └── java.security.Key (interface)
       └── javax.crypto.SecretKey (interface)
             └── javax.crypto.spec.SecretKeySpec (class)

java.lang.Object
 └── javax.crypto.CipherSpi (abstract)
       └── javax.crypto.Cipher (final class)

java.lang.Object
 └── java.security.spec.AlgorithmParameterSpec (interface)
       ├── javax.crypto.spec.IvParameterSpec (class)
       └── javax.crypto.spec.GCMParameterSpec (class)
```

---

## 2. Asymmetric Encryption (Public-Key Cryptography)

### Key Interfaces and Classes

* **Key** (interface)

    * **PublicKey** (interface): Represents a public key.
    * **PrivateKey** (interface): Represents a private key.

### Key Generation and Factories

* **KeyPairGenerator**: Used to generate pairs of public and private keys (e.g., RSA).
* **KeyFactory**: Converts key specifications (like encoded key material) into key objects.

### Signing and Verification

* **Signature**: Class for generating and verifying digital signatures.
* **SignatureSpi**: SPI interface for Signature (usually not used directly).

### Class Hierarchy Tree

```
java.lang.Object
 └── java.security.Key (interface)
       ├── java.security.PublicKey (interface)
       └── java.security.PrivateKey (interface)

java.lang.Object
 └── java.security.KeyPairGenerator (class)

java.lang.Object
 └── java.security.KeyFactory (class)

java.lang.Object
 └── java.security.SignatureSpi (abstract class)
       └── java.security.Signature (class)
```

---

## 3. Hashing and Message Digests

### Classes

* **MessageDigest**: Computes cryptographic hash functions (e.g., SHA-256).
* **DigestInputStream / DigestOutputStream**: Wrap streams to compute digests during I/O.

### Class Hierarchy Tree

```
java.lang.Object
 └── java.security.MessageDigest (class)

java.io.FilterInputStream
 └── java.security.DigestInputStream (class)

java.io.FilterOutputStream
 └── java.security.DigestOutputStream (class)
```

---

## 4. Cipher Modes of Operation

### ECB (Electronic Codebook)

* Simple, but insecure due to pattern leaks.

### CBC (Cipher Block Chaining)

* Requires IV; XORs each plaintext block with previous ciphertext block.

### GCM (Galois/Counter Mode)

* Provides confidentiality and integrity. Requires IV and tag length.

### CTR (Counter Mode)

* Converts block cipher into stream cipher using a counter.

---

## 5. Example Cipher Usage (AES + RSA)

```java
// AES Key Generation
KeyGenerator keyGen = KeyGenerator.getInstance("AES");
keyGen.init(128);
SecretKey secretKey = keyGen.generateKey();

// RSA Key Pair
KeyPairGenerator pairGen = KeyPairGenerator.getInstance("RSA");
pairGen.initialize(2048);
KeyPair pair = pairGen.generateKeyPair();

// AES Encryption
Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
IvParameterSpec ivSpec = new IvParameterSpec(new byte[16]);
aesCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
byte[] encrypted = aesCipher.doFinal("Hello World".getBytes());

// Signing with RSA
Signature signature = Signature.getInstance("SHA256withRSA");
signature.initSign(pair.getPrivate());
signature.update(encrypted);
byte[] digitalSig = signature.sign();
```

---

## 6. Summary Table

| Category       | Classes/Interfaces                                                   |
| -------------- | -------------------------------------------------------------------- |
| Key Management | `Key`, `SecretKey`, `SecretKeySpec`, `PublicKey`, `PrivateKey`       |
| Ciphers        | `Cipher`, `CipherSpi`                                                |
| Key Generators | `KeyGenerator`, `KeyPairGenerator`, `KeyFactory`, `SecretKeyFactory` |
| Parameters     | `AlgorithmParameterSpec`, `IvParameterSpec`, `GCMParameterSpec`      |
| Signing        | `Signature`, `SignatureSpi`                                          |
| Hashing        | `MessageDigest`, `DigestInputStream`, `DigestOutputStream`           |

---

## 7. Security Notes

* Always use secure modes (avoid ECB).
* Use strong key sizes (AES-256, RSA-2048+).
* Protect private keys with secure storage (HSMs, KeyVaults).
* Use random IVs and secure random number generators.

---

## 8. Advanced Trends

* Transitioning to ECC for shorter, faster keys.
* Post-Quantum Cryptography emerging as a new standard.
* Hybrid encryption widely adopted in HTTPS and secure messaging.

---

This documentation gives a full walkthrough of cryptographic structures in Java. It's suitable for developers implementing secure data transmission, authentication, and storage features.
