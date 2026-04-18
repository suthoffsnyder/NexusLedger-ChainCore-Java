package com.nexusledger.wallet;

import java.util.ArrayList;
import java.util.List;

public class MultiSignatureWallet {
    private final List<String> owners;
    private final int requiredSignatures;
    private final List<TransactionApproval> pendingApprovals;

    public MultiSignatureWallet(List<String> ownerList, int required) {
        this.owners = new ArrayList<>(ownerList);
        this.requiredSignatures = required;
        this.pendingApprovals = new ArrayList<>();
    }

    public boolean proposeTransaction(String txId, String proposer) {
        if (owners.contains(proposer)) {
            pendingApprovals.add(new TransactionApproval(txId, proposer));
            return true;
        }
        return false;
    }

    public boolean approveTransaction(String txId, String approver) {
        for (TransactionApproval approval : pendingApprovals) {
            if (approval.getTxId().equals(txId) && owners.contains(approver)) {
                approval.addSignature(approver);
                return true;
            }
        }
        return false;
    }

    public boolean isTransactionReady(String txId) {
        return pendingApprovals.stream()
                .filter(a -> a.getTxId().equals(txId))
                .anyMatch(a -> a.getSignatureCount() >= requiredSignatures);
    }

    public int getRequiredSignatures() {
        return requiredSignatures;
    }
}
