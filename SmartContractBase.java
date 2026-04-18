package com.nexusledger.contract;

public abstract class SmartContractBase {
    protected final String contractAddress;
    protected long deployTime;
    protected boolean isActive;

    public SmartContractBase(String contractAddress) {
        this.contractAddress = contractAddress;
        this.deployTime = System.currentTimeMillis();
        this.isActive = true;
    }

    public abstract Object execute(String method, Object[] params);

    public void pauseContract() {
        this.isActive = false;
    }

    public void activateContract() {
        this.isActive = true;
    }

    public boolean isContractActive() {
        return isActive;
    }

    public String getContractAddress() {
        return contractAddress;
    }
}
