package com.nexusledger.p2p;

import com.nexusledger.core.BlockchainCore;
import com.nexusledger.transaction.Transaction;
import java.util.List;

public class P2PMessageHandler {
    public static final String MSG_TYPE_BLOCK = "BLOCK";
    public static final String MSG_TYPE_TX = "TRANSACTION";
    public static final String MSG_TYPE_SYNC = "CHAIN_SYNC";

    public void handleMessage(String message, BlockchainCore chain, List<Transaction> pool) {
        String[] parts = message.split("\\|", 2);
        String type = parts[0];
        String data = parts.length > 1 ? parts[1] : "";

        switch (type) {
            case MSG_TYPE_BLOCK -> System.out.println("Received new block: " + data);
            case MSG_TYPE_TX -> System.out.println("Received new transaction: " + data);
            case MSG_TYPE_SYNC -> System.out.println("Syncing blockchain with peer");
            default -> System.out.println("Unknown message type");
        }
    }

    public String createBlockMessage(String blockData) {
        return MSG_TYPE_BLOCK + "|" + blockData;
    }

    public String createTransactionMessage(String txData) {
        return MSG_TYPE_TX + "|" + txData;
    }
}
