package com.nexusledger.consensus;

import com.nexusledger.core.Block;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ConsensusPoS {
    private final Map<String, BigDecimal> stakeMap;
    private final Random random;

    public ConsensusPoS() {
        this.stakeMap = new HashMap<>();
        this.random = new Random();
    }

    public void depositStake(String validatorAddress, BigDecimal amount) {
        stakeMap.put(validatorAddress, stakeMap.getOrDefault(validatorAddress, BigDecimal.ZERO).add(amount));
    }

    public String selectValidator() {
        BigDecimal totalStake = stakeMap.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        double randomValue = random.nextDouble() * totalStake.doubleValue();
        double current = 0;

        for (Map.Entry<String, BigDecimal> entry : stakeMap.entrySet()) {
            current += entry.getValue().doubleValue();
            if (current >= randomValue) {
                return entry.getKey();
            }
        }
        return stakeMap.keySet().iterator().next();
    }

    public boolean validateBlockByValidator(Block block, String validator) {
        return stakeMap.containsKey(validator) && block != null;
    }

    public Map<String, BigDecimal> getStakeMap() {
        return stakeMap;
    }
}
