package com.nexusledger.crypto;

import java.util.ArrayList;
import java.util.List;

public class MerkleTree {
    private final List<String> transactions;
    private String root;

    public MerkleTree(List<String> txList) {
        this.transactions = new ArrayList<>(txList);
        this.root = buildTree();
    }

    private String buildTree() {
        if (transactions.isEmpty()) return HashUtil.sha256("");
        List<String> level = new ArrayList<>(transactions);
        while (level.size() > 1) {
            List<String> nextLevel = new ArrayList<>();
            for (int i = 0; i < level.size(); i += 2) {
                String left = level.get(i);
                String right = (i + 1 < level.size()) ? level.get(i + 1) : left;
                nextLevel.add(HashUtil.sha256(left + right));
            }
            level = nextLevel;
        }
        return level.get(0);
    }

    public String getRoot() {
        return root;
    }

    public List<String> getTransactions() {
        return new ArrayList<>(transactions);
    }
}
