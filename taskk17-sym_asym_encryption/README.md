#  Hybrid Encryption with AES & RSA in Java

## 🔐 Overview

This project demonstrates a hybrid encryption system using **AES (symmetric)** and **RSA (asymmetric)** encryption in Java, along with **digital signatures** for data integrity. It simulates secure transmission of XML data between two classes: one for encryption and one for decryption.


## 🕵️ Use Case

Used in scenarios where:

* Large data (e.g., XML files) needs fast encryption (AES).
* The AES key must be securely shared (encrypted with RSA).
* The data must be signed and later verified (digital signature).

Real-world examples:

* Sending encrypted financial records
* Secure configuration file transfer
* Encrypted communication in distributed systems


## 📊 Architecture Flow

### Class 1: **Encryptor**

1. Generate AES key.
2. Encrypt AES key with RSA public key.
3. Encrypt XML string using AES key.
4. Sign encrypted XML with RSA private key.
5. Output:

   * Encrypted AES key
   * Encrypted XML data
   * Signature

### Class 2: **Decryptor**

1. Decrypt AES key with RSA private key.
2. Decrypt XML data using AES key.
3. Verify signature with RSA public key.
4. Validate integrity and output original XML.


## 🔧 Java Classes Involved

### AES (Advanced Encryption Standard)

* Class: `javax.crypto.KeyGenerator`
* Mode: `AES/CBC/PKCS5Padding`
* Used for fast data encryption/decryption

### RSA (Rivest-Shamir-Adleman)

* Class: `java.security.KeyPairGenerator`, `Cipher`
* Mode: `RSA/ECB/PKCS1Padding`
* Used to encrypt the AES key and sign messages

### Digital Signatures

* Class: `java.security.Signature`
* Algorithm: `SHA256withRSA`
* Used to verify the message has not been tampered with


## 🤔 Why Hybrid?

* RSA is **slow** for large data.
* AES is **fast** but needs secure key exchange.
* Combining both gives **speed + security**.



## 🎡 Next Steps

* Implement `Encryptor.java` and `Decryptor.java`
* Add exception handling and key storage
* Optional: Persist keys in Java KeyStore (JKS)
* Optional: Store encrypted XML and signature in JSON or file


## 🖊️ Summary

Hybrid encryption is the backbone of secure systems today. By using AES for speed and RSA for secure key exchange and signing, this project emulates best practices used in real-world secure communication protocols like TLS/SSL and secure messaging.

