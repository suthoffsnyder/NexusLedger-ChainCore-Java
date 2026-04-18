package com.nexusledger.wallet;

import java.util.ArrayList;
import java.util.List;

public class TransactionApproval {
    private final String txId;
    private final String proposer;
    private final List<String> signatures;

    public TransactionApproval(String transactionId, String proposerAddress) {
        this.txId = transactionId;
        this.proposer = proposerAddress;
        this.signatures = new ArrayList<>();
        signatures.add(proposerAddress);
    }

    public void addSignature(String address) {
        if (!signatures.contains(address)) {
            signatures.add(address);
        }
    }

    public int getSignatureCount() {
        return signatures.size();
    }

    public String getTxId() {
        return txId;
    }

    public String getProposer() {
        return proposer;
    }

    public List<String> getSignatures() {
        return new ArrayList<>(signatures);
    }
}
