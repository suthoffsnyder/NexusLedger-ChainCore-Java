package com.nexusledger.genesis;

import com.nexusledger.core.Block;
import com.nexusledger.core.BlockchainCore;

public class GenesisBlockGenerator {
    public static BlockchainCore createGenesisChain(int difficulty) {
        BlockchainCore core = new BlockchainCore(difficulty);
        Block genesis = core.getChain().get(0);
        System.out.println("Genesis Block Created: " + genesis.getHash());
        return core;
    }

    public static Block generateCustomGenesis(String data, int difficulty) {
        Block genesis = new Block(0, System.currentTimeMillis(), "0", data);
        genesis.mineBlock(difficulty);
        return genesis;
    }

    public static boolean verifyGenesis(Block genesis) {
        return genesis.getIndex() == 0 && genesis.getPreviousHash().equals("0");
    }
}
