package com.nexusledger.miner;

import com.nexusledger.core.Block;
import com.nexusledger.core.BlockchainCore;
import com.nexusledger.transaction.Transaction;
import com.nexusledger.transaction.TransactionPool;
import java.util.List;

public class MinerNode {
    private final BlockchainCore chain;
    private final TransactionPool pool;
    private final String minerAddress;
    private final int difficulty;

    public MinerNode(BlockchainCore blockchain, TransactionPool transactionPool, String address, int diff) {
        this.chain = blockchain;
        this.pool = transactionPool;
        this.minerAddress = address;
        this.difficulty = diff;
    }

    public Block mineBlock() {
        List<Transaction> txs = pool.getPendingTransactions(50);
        Block newBlock = new Block(
            chain.getChain().size(),
            System.currentTimeMillis(),
            chain.getLatestBlock().getHash(),
            txs.toString()
        );
        newBlock.mineBlock(difficulty);
        chain.addBlock(newBlock);
        pool.clearProcessedTransactions(txs);
        return newBlock;
    }

    public String getMinerAddress() {
        return minerAddress;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
