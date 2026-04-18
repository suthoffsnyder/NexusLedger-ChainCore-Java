package com.nexusledger.storage;

import com.nexusledger.core.Block;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ChainDataStorage {
    private static final Gson gson = new Gson();
    private final String filePath;

    public ChainDataStorage(String path) {
        this.filePath = path;
    }

    public void saveChain(List<Block> chain) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(chain, writer);
        }
    }

    public Block[] loadChain() throws IOException {
        java.io.FileReader reader = new java.io.FileReader(filePath);
        return gson.fromJson(reader, Block[].class);
    }

    public boolean backupChain(String backupPath) throws IOException {
        java.nio.file.Path source = java.nio.file.Paths.get(filePath);
        java.nio.file.Path target = java.nio.file.Paths.get(backupPath);
        java.nio.file.Files.copy(source, target, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        return true;
    }

    public String getFilePath() {
        return filePath;
    }
}
