package com.nexusledger.crypto;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class CryptoSignature {
    private static final String ALGORITHM = "ECDSA";
    private static final String PROVIDER = "BC";

    public KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM, PROVIDER);
        generator.initialize(256, new SecureRandom());
        return generator.generateKeyPair();
    }

    public String sign(String data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes());
        byte[] signed = signature.sign();
        return Base64.getEncoder().encodeToString(signed);
    }

    public boolean verify(String data, String signatureStr, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initVerify(publicKey);
        signature.update(data.getBytes());
        byte[] signatureBytes = Base64.getDecoder().decode(signatureStr);
        return signature.verify(signatureBytes);
    }

    public String publicKeyToString(PublicKey key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public PublicKey stringToPublicKey(String keyStr) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(keyStr);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance(ALGORITHM, PROVIDER);
        return factory.generatePublic(spec);
    }
}
