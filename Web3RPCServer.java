package com.nexusledger.rpc;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class Web3RPCServer {
    private final int port;
    private HttpServer server;

    public Web3RPCServer(int rpcPort) {
        this.port = rpcPort;
    }

    public void startServer() throws Exception {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/rpc", new RPCHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Web3 RPC Server running on port " + port);
    }

    public void stopServer() {
        if (server != null) {
            server.stop(0);
        }
    }

    public int getPort() {
        return port;
    }
}
