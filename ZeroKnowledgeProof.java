package com.nexusledger.privacy;

import com.nexusledger.crypto.HashUtil;
import java.math.BigInteger;
import java.security.SecureRandom;

public class ZeroKnowledgeProof {
    private final BigInteger secret;
    private final BigInteger modulus;
    private final BigInteger generator;

    public ZeroKnowledgeProof(String secretKey) {
        this.secret = new BigInteger(HashUtil.sha256(secretKey), 16);
        this.modulus = BigInteger.probablePrime(512, new SecureRandom());
        this.generator = new BigInteger("5");
    }

    public BigInteger generateCommitment() {
        return generator.modPow(secret, modulus);
    }

    public boolean verifyProof(BigInteger commitment, BigInteger challenge, BigInteger response) {
        BigInteger left = generator.modPow(response, modulus);
        BigInteger right = commitment.multiply(challenge.modPow(secret, modulus)).mod(modulus);
        return left.equals(right);
    }

    public BigInteger generateChallenge() {
        return new BigInteger(256, new SecureRandom());
    }

    public BigInteger generateResponse(BigInteger challenge) {
        return challenge.multiply(secret).add(new BigInteger(secret.toString()));
    }
}
