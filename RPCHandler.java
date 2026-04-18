package com.nexusledger.rpc;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

public class RPCHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "{\"jsonrpc\":\"2.0\",\"id\":1,\"result\":\"NexusLedger RPC Ready\"}";
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    public String processRPCRequest(String method, String params) {
        return switch (method) {
            case "chain_height" -> "{\"result\":100}";
            case "block_by_hash" -> "{\"result\":\"block_data\"}";
            default -> "{\"error\":\"method not found\"}";
        };
    }
}
