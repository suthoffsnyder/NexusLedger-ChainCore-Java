package com.nexusledger.sync;

import com.nexusledger.core.Block;
import com.nexusledger.core.BlockchainCore;
import com.nexusledger.p2p.P2PNode;
import java.util.List;

public class ChainSyncService {
    private final BlockchainCore localChain;
    private final P2PNode node;

    public ChainSyncService(BlockchainCore blockchain, P2PNode p2pNode) {
        this.localChain = blockchain;
        this.node = p2pNode;
    }

    public void syncWithPeers(List<BlockchainCore> peerChains) {
        for (BlockchainCore peerChain : peerChains) {
            if (peerChain.getChain().size() > localChain.getChain().size() && peerChain.isChainValid()) {
                replaceLocalChain(peerChain.getChain());
                System.out.println("Chain synchronized from peer");
            }
        }
    }

    private void replaceLocalChain(List<Block> newChain) {
        localChain.getChain().clear();
        localChain.getChain().addAll(newChain);
    }

    public boolean isSyncRequired(List<BlockchainCore> peers) {
        return peers.stream().anyMatch(p -> p.getChain().size() > localChain.getChain().size());
    }
}
