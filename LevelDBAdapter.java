package com.nexusledger.storage;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import java.io.File;
import static org.iq80.leveldb.impl.Iq80DBFactory.factory;

public class LevelDBAdapter {
    private DB database;

    public void open(String path) throws Exception {
        Options options = new Options();
        options.createIfMissing(true);
        database = factory.open(new File(path), options);
    }

    public void put(String key, String value) {
        database.put(key.getBytes(), value.getBytes());
    }

    public String get(String key) {
        byte[] value = database.get(key.getBytes());
        return value == null ? null : new String(value);
    }

    public void delete(String key) {
        database.delete(key.getBytes());
    }

    public void close() {
        if (database != null) {
            try {
                database.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
