# Symmetric Encryption in Java using AES-128-GCM

---

## 🔐 What is Symmetric Encryption?

Symmetric encryption is a cryptographic method where the **same secret key** is used for both **encryption and decryption** of data. This means that the sender and receiver must both possess the same key and keep it confidential. It is widely used due to its **efficiency** and **speed**, especially for encrypting large volumes of data.

### Key Features:

* **One key for both encryption and decryption**
* **Fast and lightweight** compared to asymmetric encryption
* Suitable for **secure data transmission**, **file storage**, and **authenticated encryption**

Common symmetric algorithms include:

* AES (Advanced Encryption Standard)
* DES (Data Encryption Standard)
* Blowfish

In this documentation, we focus on **AES-128 in GCM mode**, which provides **authenticated encryption**—ensuring confidentiality, integrity, and authenticity.

---

## ✨ Class Hierarchy Tree

```
Object
 └── Key (interface)
       └── SecretKey (interface)
             └── SecretKeySpec (class)

Object
 └── CipherSpi (abstract)
       └── Cipher (final class)

Object
 └── AlgorithmParameterSpec (interface)
       └── GCMParameterSpec (class)
```

---

## 🔑 Class Responsibilities & Methods

### 1. `Key` (Interface)

* Root interface for all key types.
* **Methods:**

  * `String getAlgorithm()` – Returns the encryption algorithm (e.g., "AES").
  * `String getFormat()` – Returns encoding format (e.g., "RAW").
  * `byte[] getEncoded()` – Returns the key in byte format for transport or storage.

### 2. `SecretKey` (Interface)

* Extends `Key` for symmetric keys.
* **Methods:**

  * Inherits all from `Key`.

### 3. `SecretKeySpec` (Class)

* Concrete class to wrap raw byte arrays into a symmetric key.
* **Constructor:**

  * `SecretKeySpec(byte[] key, String algorithm)` – Creates a key object from byte array.
* **Methods:**

  * `getEncoded()` – Returns the key bytes.
  * `getAlgorithm()` – Returns "AES".
  * `getFormat()` – Returns "RAW".

### 4. `AlgorithmParameterSpec` (Interface)

* Marker interface for cryptographic parameter classes.
* No methods – used for type enforcement.

### 5. `GCMParameterSpec` (Class)

* Holds IV (nonce) and authentication tag length for AES-GCM.
* **Constructor:**

  * `GCMParameterSpec(int tagLength, byte[] iv)` – Creates spec with tag and IV.
* **Methods:**

  * `int getTLen()` – Returns tag length in bits.
  * `byte[] getIV()` – Returns the IV.

### 6. `CipherSpi` (Abstract Class)

* SPI for cryptographic service providers.
* Not used directly by application developers.
* **Methods:** Implemented by provider classes to handle core crypto operations.

### 7. `Cipher` (Final Class)

* Core class for encryption and decryption in Java.
* **Key Methods:**

  * `static Cipher getInstance(String transformation)` – Gets a cipher object for algorithm.
  * `void init(int opmode, Key key, AlgorithmParameterSpec spec)` – Initializes for encrypt/decrypt.
  * `void init(int opmode, Key key, AlgorithmParameterSpec spec, SecureRandom random)` – Initializes cipher with secure random source.
  * `byte[] doFinal(byte[] input)` – Performs final encryption/decryption step.
  * `int getBlockSize()` – Returns block size of the cipher (0 for AES-GCM).
  * `int getOutputSize(int inputLen)` – Calculates output buffer size needed.
  * `byte[] update(byte[] input)` – Performs partial encryption (optional).
  * `void updateAAD(byte[] src)` – Adds Additional Authenticated Data for integrity (optional).

---

## 🛠️ How AES-128-GCM Works in Java

### Step-by-step Workflow:

1. **Generate a 128-bit AES key** (`SecretKeySpec`)
2. **Generate IV (Initialization Vector)** of 12 bytes
3. **Configure `GCMParameterSpec`** with IV and tag length
4. **Initialize `Cipher`** in `ENCRYPT_MODE`
5. **Encrypt plaintext** and get ciphertext
6. **Base64 Encode** the ciphertext and IV (for transmission and logging)
7. **Initialize `Cipher`** in `DECRYPT_MODE` with same key and IV
8. **Decrypt ciphertext** back to plaintext

---

## ✅ Use Cases

* **API Token Encryption**: Storing encrypted JWTs or access tokens
* **Local File Security**: Encrypting documents or logs
* **Password Storage**: Encrypting before storing in DB
* **Mobile Applications**: Secure local storage on Android/iOS
* **Cloud Services**: Encrypt data-at-rest before writing to S3, GCP buckets

---

### 🔐What are supposed to be hidden:
#### SECRET: 
The secretKey (or keyBytes) must be securely stored and protected.

#### PUBLIC: 
The IV, cipherText, and algorithm (AES/GCM/NoPadding) are fine to share.

### 🔐 Where to Store secretKey Securely?
1. Environment	Suggested Storage for Secret Key
2. Dev machine	Environment variable, .env file (with .gitignore)
3. Server app	Java KeyStore (.jks), OS Vault, or Cloud KMS
4. Android	Android Keystore API
5. Cloud	AWS KMS, GCP KMS, Azure Key Vault

### ⚠️ Note on `[B@...` Outputs

If you print a byte array directly like `System.out.println(cipherText);`, Java gives a memory reference (e.g., `[B@3f197a46`) instead of the actual content. To inspect the contents:

```java
System.out.println(Arrays.toString(cipherText));
```

To make it human-readable (e.g., for storage or transport), use Base64:

```java
String base64 = Base64.getEncoder().encodeToString(cipherText);
```

**Reminder:** Base64 is not encryption—it's just an encoding format applied **after encryption** to make the encrypted data readable as text.

---

## 📦 Understanding Base64 in the Encryption Process

After encrypting data using `Cipher.doFinal()`, the result is a **binary byte array** that may contain unreadable characters or be unsafe for textual transmission (like in JSON, URLs, etc.). To address this:

* We use **Base64 encoding** to convert this binary data into a **readable ASCII string**.
* This helps in **logging, transmitting over APIs**, or **storing encrypted content** in text-based formats.
* It’s important to remember that **Base64 does not provide security**—only formatting.

**Why do it?**

* Makes byte arrays printable and portable
* Maintains encryption fidelity (you can decode and get exact original bytes)
* Required when passing ciphertext in environments that expect strings

---

## 🤔 Why AES-GCM?

| Feature        | Value                      |
| -------------- | -------------------------- |
| Encryption     | Yes                        |
| Authentication | Yes (via tag)              |
| Integrity      | Ensured via GCM            |
| Speed          | Fast with hardware support |
| IV Requirement | 12 bytes recommended       |

---

## 🚀 Extend Your Learning

* Use `KeyGenerator` to securely generate keys.
* Explore storing keys in `KeyStore`
* Learn about `Mac` and `Signature` for authentication
* Consider AES-256 for stronger security (32-byte keys)
* Look into integrating `Bouncy Castle` for extended crypto features

---

## 💡 Summary

AES-128-GCM in Java is a modern and secure symmetric encryption solution. It combines both **confidentiality and integrity**, is fast, and widely supported. Great for encrypting sensitive data in memory, at rest, or in transit.
