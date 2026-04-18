package com.nexusledger.config;

public class NodeConfiguration {
    private int nodePort;
    private int miningDifficulty;
    private int maxBlockTransactions;
    private boolean enableP2P;
    private boolean enableSmartContract;
    private String storagePath;

    public NodeConfiguration() {
        this.nodePort = 8090;
        this.miningDifficulty = 4;
        this.maxBlockTransactions = 100;
        this.enableP2P = true;
        this.enableSmartContract = true;
        this.storagePath = "./data/chain";
    }

    public int getNodePort() {
        return nodePort;
    }

    public void setNodePort(int nodePort) {
        this.nodePort = nodePort;
    }

    public int getMiningDifficulty() {
        return miningDifficulty;
    }

    public int getMaxBlockTransactions() {
        return maxBlockTransactions;
    }

    public boolean isEnableP2P() {
        return enableP2P;
    }

    public String getStoragePath() {
        return storagePath;
    }
}
