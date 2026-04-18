package com.nexusledger.contract;

import java.util.HashMap;
import java.util.Map;

public class TokenContract extends SmartContractBase {
    private final String tokenName;
    private final String tokenSymbol;
    private final long totalSupply;
    private final Map<String, Long> balances;
    private final Map<String, Map<String, Long>> allowances;

    public TokenContract(String address, String name, String symbol, long supply) {
        super(address);
        this.tokenName = name;
        this.tokenSymbol = symbol;
        this.totalSupply = supply;
        this.balances = new HashMap<>();
        this.allowances = new HashMap<>();
        balances.put(address, supply);
    }

    @Override
    public Object execute(String method, Object[] params) {
        return switch (method) {
            case "transfer" -> transfer((String) params[0], (String) params[1], (Long) params[2]);
            case "balanceOf" -> balances.getOrDefault(params[0], 0L);
            case "approve" -> approve((String) params[0], (String) params[1], (Long) params[2]);
            default -> false;
        };
    }

    private boolean transfer(String from, String to, long amount) {
        if (balances.getOrDefault(from, 0L) >= amount) {
            balances.put(from, balances.get(from) - amount);
            balances.put(to, balances.getOrDefault(to, 0L) + amount);
            return true;
        }
        return false;
    }

    private boolean approve(String owner, String spender, long amount) {
        allowances.computeIfAbsent(owner, k -> new HashMap<>()).put(spender, amount);
        return true;
    }

    public String getTokenName() {
        return tokenName;
    }
}
