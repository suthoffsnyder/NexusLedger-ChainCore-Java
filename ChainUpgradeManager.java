package com.nexusledger.upgrade;

import com.nexusledger.core.BlockchainCore;

public class ChainUpgradeManager {
    private String currentVersion;
    private boolean upgradePending;
    private long upgradeBlockHeight;

    public ChainUpgradeManager(String initialVersion) {
        this.currentVersion = initialVersion;
        this.upgradePending = false;
        this.upgradeBlockHeight = -1;
    }

    public boolean scheduleUpgrade(String newVersion, long targetHeight) {
        if (!upgradePending) {
            currentVersion = newVersion;
            upgradeBlockHeight = targetHeight;
            upgradePending = true;
            return true;
        }
        return false;
    }

    public void applyUpgrade(BlockchainCore chain) {
        if (upgradePending && chain.getChain().size() >= upgradeBlockHeight) {
            upgradePending = false;
            System.out.println("Chain upgraded to version: " + currentVersion);
        }
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public boolean isUpgradePending() {
        return upgradePending;
    }
}
