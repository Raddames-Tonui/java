package sym_asym_encryption;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;

public class Decryptor {
    public static void main(String[] args) throws Exception {
        // Replace with outputs from Encryptor
        String encryptedAESKeyB64 = "bYx48uN7Kxx+QEw56jlAQn+1qbQlQuwxJrYhotPK5KRpljc5ie4vAH5oyWXAfPzFYQu5oHmiwiX49m2YrljblSA64JZG5pTvvqf6P7k3EU+jLOALLp/jrq6laD4pyPoBRVLW19yzsSVtqFhbHCKQQSpbvnjdbV2ljh7w/cRgjIg8Mlf8OMhc/E0/zUQmabzc73X6xJNykxAFm3zaase/OedvuZI/cKVbnyF1cy0Gs4pdPf7f1wiy9SnBQA2r9cq67uxdaj87omSKAhn5eNe0u+OWPCGlH8zr02JnxUPq2392XjNumegu74RnE0OcCkaBDwqiJgV/5meAHgwnbArqCw==";
        String encryptedXMLB64 = "sXbaYTW22A8smWEqD6+jBUDkzACJEWH1k4HYosjQ/LXi+Z7iJgzPK5XKU5QKfmYqNh8MDgKihpHg1QrE6YI3Vg==";
        String ivB64 = "Hr88aQQUoYurWZOj+SIKfQ==";
        String signatureB64 = "pa5ULrXwvZj0R/2So87CvMnSB1Y/JVho9uAANTIzPHaFjw5ov/g3LyxcfhfB/C5borevqmePFfF4h8IOhlkp4pp42EfNhsJ5fNqMhMw/vNnt0O90Ch+ulSh8Lfs8SrK7p4LZLRVW3jmTSL8Nphu84v5ppz9R4ZU2V45Au6qabglY811sYHLOR4mxxoJ4wmK1hhgAXf982OIeUcFaYVJ2gPqp8hePO1o0gIrSUJu2ZEvPwUwWG92QQf1X0quNAefflQMqXRnJrOJolU06ZU2q66reHkFAyofWsb5+XRmupDuqw1CGrCUVUCyiFZQZKH0+gLyAwJPKP5ceFmcvpLZsXw==";
        String publicKeyB64 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqrhY+IgEoSKcbW1oYjWBPDjHvLVEx/2ilIsoKJtw3RLZSaHdI/e1R2bs9HeRsJ0dL9l8tbVqf6npu/WVVOqAJdFYIfXtBRreoNBDPLBp7MiMntcOFUTo/Z/bY1hBYtfNVNlObA2YZDZkME8JaTlRBzERs7iiSqBS0XrF3Ks1vg36GTkq1veT6nDKkcSety9stimF7hC2EuuojiVL3aGWfcH1hGwQV/bhHhg1hiKf+pja2oiKHjw21BFWzanyqD96KDZXqncD+34cl+CHC7TfxZzkkhTEI47EVigfy+pdF8T8drSHpyVQ8aqlBcYP8nwcM5e9PHjqlL5H0kl4j3X4twIDAQAB";
        String privateKeyB64 = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCquFj4iAShIpxtbWhiNYE8OMe8tUTH/aKUiygom3DdEtlJod0j97VHZuz0d5GwnR0v2Xy1tWp/qem79ZVU6oAl0Vgh9e0FGt6g0EM8sGnsyIye1w4VROj9n9tjWEFi181U2U5sDZhkNmQwTwlpOVEHMRGzuKJKoFLResXcqzW+DfoZOSrW95PqcMqRxJ63L2y2KYXuELYS66iOJUvdoZZ9wfWEbBBX9uEeGDWGIp/6mNraiIoePDbUEVbNqfKoP3ooNleqdwP7fhyX4IcLtN/FnOSSFMQjjsRWKB/L6l0XxPx2tIenJVDxqqUFxg/yfBwzl708eOqUvkfSSXiPdfi3AgMBAAECggEAF4R1nSb/nELsUZa0vnKobudTfIdsBjDGaLgSvCVNkua50A7gK8g/zKo9EiA2jaHXF3RaXZ20IgoCDMZz39K9/HeuMF90WzOsX3LREFctAFVQKcFuSzfG8MlIq5LIzxjzTDDzQKdOyt9DRfRdU4V1yAvWaG+Sy7zyK7FB+HgNWzTQBGhSrNG+fqSjuvdwmdDg/3m4X9yPvCsoo415nHti1iTCkJcVxKY+Gjd+s4Q219Ol2jvsC7fsIkPq5tpwoHL5AUj37KjaXLXqJuX5USFrRjSFPvbjcnAnmMPe9RZnTsg0GeOaLG1Ir/bIex4OpCsvnXdWVjCuJM+pHS5pb3p3wQKBgQDctPvpvi1p7ND2Vwk3fC+Nlp8bxmKu5x2smVTBiPQ676bXiLmNcd4GTIuCDqCCyREqAXJ2CFo4AIcTta/lDNlq2+a02Rxkul23HgDroFtxHnWbN7VtIENqS2v32JF6vCQED3SX+PwVprKWHhit9neNbDLm9z951O3G5GXXRDBCPwKBgQDGBRDuYLBvn2SNzcEWNaBmxPNWQsMF5xm6qPyhL+Yz7Src89L/VV3aPQf3xtKxNjknNdFHQBPoFE3jDsBVqdCjT25XAPhsglaIRkL2Eju0FGMwwSGuig3uYCi2KIFAlJT6nMtydVm+8Zrse7z8SujjYBfwxL3pRxRG8fxlWiI7iQKBgAulOQ2STU8jgbsdyIy27sNnySlsBFT6OOScio5TrZ9WedUxj6jY80Ad1dac8vczqMYwoVt9AVWnIXl2wrsxPF+ZDslIE2l1M/CvkpjcMzb6xnXUMKx7YVNAe4fuU27IcpYMQriqGJMD4rRtDhxPvuslZGIvdZf2Fj3CW2VKYcl9AoGAdhItP0XNzjDHPzpRCFIfe0eyJFwYdxuOHtuGDwJFi+69YRPNWsvm8yry5k3Z8Sp4t6WCTK6rb/UzX9C65BkmO0owa5Y7ik/JEuZ6JMlIah7B6h8uWykcF2ekvsC2GPC/SBNXx76rLYxYKOu7CuALrw/sFnbpGgB02byOF0zAHqkCgYAloqQvg2cgIm/Md0N+UbAy1TK7j/HtrYc50eHQXqJ58w9lRvppG4C+FTrZWZ76uIMCiqKa7P/X6askRT4POykVCHiIv7+GY5XplntGLzXjuAcZKVkPJFyJkmneMkg1c/rYAY//0VJ0m0LPcQYhUDKInoAMB/jfKsAZi2UDIfIMKg==";

        // 1. Decode everything from Base64
        byte[] encryptedAESKey = Base64.getDecoder().decode(encryptedAESKeyB64);
        byte[] encryptedXML = Base64.getDecoder().decode(encryptedXMLB64);
        byte[] iv = Base64.getDecoder().decode(ivB64);
        byte[] digitalSignature = Base64.getDecoder().decode(signatureB64);
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyB64);
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyB64);

        // 2. Rebuild RSA key pair
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));

        // 3. Decrypt AES key using RSA private key
        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] aesKeyBytes = rsaCipher.doFinal(encryptedAESKey);
        SecretKeySpec aesKey = new SecretKeySpec(aesKeyBytes, "AES");

        // 4. Decrypt the XML using AES key and IV
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aesCipher.init(Cipher.DECRYPT_MODE, aesKey, new IvParameterSpec(iv));
        byte[] decryptedXML = aesCipher.doFinal(encryptedXML);

        // 5. Verify the digital signature using RSA public key
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(publicKey);
        verifier.update(encryptedXML);
        boolean isValid = verifier.verify(digitalSignature);

        // 6. Output
        System.out.println("🗃️ Decrypted XML: " + new String(decryptedXML));
        System.out.println("✅ Signature Valid: " + isValid);
    }
}
