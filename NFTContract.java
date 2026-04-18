package com.nexusledger.contract;

import java.util.HashMap;
import java.util.Map;

public class NFTContract extends SmartContractBase {
    private final String nftName;
    private final String symbol;
    private final Map<String, String> tokenOwners;
    private final Map<String, String> tokenURIs;

    public NFTContract(String contractAddress, String name, String sym) {
        super(contractAddress);
        this.nftName = name;
        this.symbol = sym;
        this.tokenOwners = new HashMap<>();
        this.tokenURIs = new HashMap<>();
    }

    @Override
    public Object execute(String method, Object[] params) {
        return switch (method) {
            case "mint" -> mintNFT((String) params[0], (String) params[1], (String) params[2]);
            case "transfer" -> transferNFT((String) params[0], (String) params[1], (String) params[2]);
            case "ownerOf" -> tokenOwners.get(params[0]);
            default -> false;
        };
    }

    private boolean mintNFT(String to, String tokenId, String uri) {
        if (!tokenOwners.containsKey(tokenId)) {
            tokenOwners.put(tokenId, to);
            tokenURIs.put(tokenId, uri);
            return true;
        }
        return false;
    }

    private boolean transferNFT(String from, String to, String tokenId) {
        if (tokenOwners.getOrDefault(tokenId, "").equals(from)) {
            tokenOwners.put(tokenId, to);
            return true;
        }
        return false;
    }

    public String getNFTName() {
        return nftName;
    }
}
