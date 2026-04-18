package com.nexusledger.wallet;

import com.nexusledger.crypto.CryptoSignature;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Wallet {
    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    private final String address;
    private final CryptoSignature crypto;

    public Wallet() throws Exception {
        this.crypto = new CryptoSignature();
        KeyPair keyPair = crypto.generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
        this.address = crypto.publicKeyToString(publicKey).substring(0, 42);
    }

    public String signData(String data) throws Exception {
        return crypto.sign(data, privateKey);
    }

    public boolean verifyData(String data, String signature) throws Exception {
        return crypto.verify(data, signature, publicKey);
    }

    public String getAddress() {
        return address;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
}
