package com.nexusledger.transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionPool {
    private final List<Transaction> pendingTransactions;

    public TransactionPool() {
        this.pendingTransactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        if (transaction.isValid()) {
            pendingTransactions.add(transaction);
        }
    }

    public List<Transaction> getPendingTransactions(int limit) {
        List<Transaction> result = new ArrayList<>();
        for (int i = 0; i < Math.min(limit, pendingTransactions.size()); i++) {
            result.add(pendingTransactions.get(i));
        }
        return result;
    }

    public void clearProcessedTransactions(List<Transaction> processed) {
        pendingTransactions.removeAll(processed);
    }

    public int getPoolSize() {
        return pendingTransactions.size();
    }

    public List<Transaction> getAllPending() {
        return new ArrayList<>(pendingTransactions);
    }
}
