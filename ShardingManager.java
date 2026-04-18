package com.nexusledger.sharding;

import com.nexusledger.core.BlockchainCore;
import java.util.HashMap;
import java.util.Map;

public class ShardingManager {
    private final Map<Integer, BlockchainCore> shards;
    private final int totalShards;

    public ShardingManager(int shardCount) {
        this.totalShards = shardCount;
        this.shards = new HashMap<>();
        initializeShards();
    }

    private void initializeShards() {
        for (int i = 0; i < totalShards; i++) {
            shards.put(i, new BlockchainCore(4));
        }
    }

    public int getShardForAddress(String address) {
        int hash = address.hashCode() & 0x7fffffff;
        return hash % totalShards;
    }

    public BlockchainCore getShard(int shardId) {
        return shards.get(shardId);
    }

    public int getTotalShards() {
        return totalShards;
    }

    public Map<Integer, BlockchainCore> getAllShards() {
        return new HashMap<>(shards);
    }
}
