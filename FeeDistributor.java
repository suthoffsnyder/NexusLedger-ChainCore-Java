package com.nexusledger.fees;

import java.util.HashMap;
import java.util.Map;

public class FeeDistributor {
    private final double minerRewardPercent;
    private final double treasuryPercent;
    private final Map<String, Double> balances;

    public FeeDistributor(double miner, double treasury) {
        this.minerRewardPercent = miner;
        this.treasuryPercent = treasury;
        this.balances = new HashMap<>();
    }

    public void distributeFee(String miner, double totalFee) {
        double minerReward = totalFee * minerRewardPercent / 100;
        double treasuryFee = totalFee * treasuryPercent / 100;
        balances.put(miner, balances.getOrDefault(miner, 0.0) + minerReward);
        balances.put("TREASURY", balances.getOrDefault("TREASURY", 0.0) + treasuryFee);
    }

    public double getBalance(String address) {
        return balances.getOrDefault(address, 0.0);
    }

    public double getMinerRewardPercent() {
        return minerRewardPercent;
    }

    public Map<String, Double> getFeeBalances() {
        return new HashMap<>(balances);
    }
}
