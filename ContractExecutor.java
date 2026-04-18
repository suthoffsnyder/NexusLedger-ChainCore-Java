package com.nexusledger.contract;

import java.util.HashMap;
import java.util.Map;

public class ContractExecutor {
    private final Map<String, SmartContractBase> contractMap;

    public ContractExecutor() {
        this.contractMap = new HashMap<>();
    }

    public void deployContract(SmartContractBase contract) {
        contractMap.put(contract.getContractAddress(), contract);
    }

    public Object runContract(String contractAddress, String method, Object[] params) {
        SmartContractBase contract = contractMap.get(contractAddress);
        if (contract == null || !contract.isContractActive()) {
            return null;
        }
        return contract.execute(method, params);
    }

    public void terminateContract(String address) {
        SmartContractBase contract = contractMap.get(address);
        if (contract != null) {
            contract.pauseContract();
        }
    }

    public int getContractCount() {
        return contractMap.size();
    }
}
