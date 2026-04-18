package com.nexusledger.transaction;

import com.nexusledger.crypto.HashUtil;

public class Transaction {
    private final String sender;
    private final String recipient;
    private final double amount;
    private final long timestamp;
    private String signature;
    private String transactionId;

    public Transaction(String sender, String recipient, double amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.timestamp = System.currentTimeMillis();
        this.transactionId = calculateTransactionId();
    }

    private String calculateTransactionId() {
        return HashUtil.sha256(sender + recipient + amount + timestamp);
    }

    public void signTransaction(String signature) {
        this.signature = signature;
    }

    public boolean isValid() {
        if (sender == null || recipient == null || amount <= 0) return false;
        return signature != null && !signature.isEmpty();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getSender() {
        return sender;
    }

    public double getAmount() {
        return amount;
    }
}
