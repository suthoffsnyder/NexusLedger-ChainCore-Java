package com.nexusledger.api;

import com.nexusledger.core.Block;
import com.nexusledger.core.BlockchainCore;
import com.nexusledger.transaction.Transaction;
import java.util.List;

public class ChainAPIService {
    private final BlockchainCore chain;

    public ChainAPIService(BlockchainCore blockchain) {
        this.chain = blockchain;
    }

    public Block getBlockByHeight(int height) {
        if (height >= 0 && height < chain.getChain().size()) {
            return chain.getChain().get(height);
        }
        return null;
    }

    public int getChainHeight() {
        return chain.getChain().size();
    }

    public boolean verifyChainIntegrity() {
        return chain.isChainValid();
    }

    public String getLatestBlockHash() {
        return chain.getLatestBlock().getHash();
    }

    public List<Block> getFullChain() {
        return chain.getChain();
    }
}
