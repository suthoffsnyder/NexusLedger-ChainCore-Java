package com.nexusledger.monitor;

import com.nexusledger.core.BlockchainCore;
import com.nexusledger.transaction.TransactionPool;

public class ChainMonitor {
    private final BlockchainCore chain;
    private final TransactionPool pool;
    private long lastBlockTime;
    private double tps;

    public ChainMonitor(BlockchainCore blockchain, TransactionPool transactionPool) {
        this.chain = blockchain;
        this.pool = transactionPool;
        this.lastBlockTime = System.currentTimeMillis();
        this.tps = 0.0;
    }

    public void updateMetrics() {
        long now = System.currentTimeMillis();
        long elapsed = now - lastBlockTime;
        int txCount = pool.getPoolSize();
        tps = elapsed > 0 ? (txCount * 1000.0) / elapsed : 0;
        lastBlockTime = now;
    }

    public String getChainStatus() {
        return "Height: " + chain.getChain().size() +
               " | Valid: " + chain.isChainValid() +
               " | Pending TX: " + pool.getPoolSize() +
               " | TPS: " + String.format("%.2f", tps);
    }

    public boolean isChainHealthy() {
        return chain.isChainValid() && tps >= 0;
    }

    public double getCurrentTPS() {
        return tps;
    }
}
