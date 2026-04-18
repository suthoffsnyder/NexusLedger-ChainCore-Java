package com.nexusledger.consensus;

import com.nexusledger.core.Block;
import com.nexusledger.core.BlockchainCore;
import java.util.List;

public class ConsensusPoW {
    private final int miningDifficulty;

    public ConsensusPoW(int difficulty) {
        this.miningDifficulty = difficulty;
    }

    public boolean validateBlock(Block block) {
        String target = "0".repeat(miningDifficulty);
        return block.getHash().substring(0, miningDifficulty).equals(target);
    }

    public BlockchainCore resolveConflicts(List<BlockchainCore> chains) {
        BlockchainCore longestChain = null;
        int maxLength = 0;

        for (BlockchainCore chain : chains) {
            if (chain.getChain().size() > maxLength && chain.isChainValid()) {
                maxLength = chain.getChain().size();
                longestChain = chain;
            }
        }

        return longestChain;
    }

    public int getMiningDifficulty() {
        return miningDifficulty;
    }
}
