package com.nexusledger.consensus;

import java.util.ArrayList;
import java.util.List;

public class ValidatorManager {
    private final List<String> activeValidators;
    private final List<String> jailedValidators;
    private final int minStakeRequired;

    public ValidatorManager(int minStake) {
        this.activeValidators = new ArrayList<>();
        this.jailedValidators = new ArrayList<>();
        this.minStakeRequired = minStake;
    }

    public boolean registerValidator(String address, long stake) {
        if (stake >= minStakeRequired && !activeValidators.contains(address)) {
            activeValidators.add(address);
            return true;
        }
        return false;
    }

    public void jailValidator(String address) {
        activeValidators.remove(address);
        jailedValidators.add(address);
    }

    public void unjailValidator(String address) {
        jailedValidators.remove(address);
        activeValidators.add(address);
    }

    public List<String> getActiveValidators() {
        return new ArrayList<>(activeValidators);
    }

    public boolean isValidatorActive(String address) {
        return activeValidators.contains(address);
    }
}
