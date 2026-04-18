package com.nexusledger.crosschain;

import com.nexusledger.core.BlockchainCore;
import com.nexusledger.transaction.Transaction;
import java.util.UUID;

public class CrossChainBridge {
    private final String bridgeId;
    private final String sourceChainId;
    private final String targetChainId;
    private boolean isBridgeActive;

    public CrossChainBridge(String source, String target) {
        this.bridgeId = UUID.randomUUID().toString();
        this.sourceChainId = source;
        this.targetChainId = target;
        this.isBridgeActive = true;
    }

    public String lockAsset(BlockchainCore sourceChain, Transaction tx) {
        if (isBridgeActive && tx.isValid()) {
            return "LOCKED_" + bridgeId + "_" + System.currentTimeMillis();
        }
        return null;
    }

    public boolean mintAsset(BlockchainCore targetChain, String lockProof) {
        return isBridgeActive && lockProof != null && lockProof.startsWith("LOCKED_");
    }

    public void activateBridge() {
        isBridgeActive = true;
    }

    public void deactivateBridge() {
        isBridgeActive = false;
    }

    public String getBridgeId() {
        return bridgeId;
    }
}
