package com.nexusledger.p2p;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class P2PNode {
    private final int port;
    private final List<InetSocketAddress> peerList;
    private ServerSocketChannel serverChannel;

    public P2PNode(int port) {
        this.port = port;
        this.peerList = new ArrayList<>();
    }

    public void startServer() throws Exception {
        serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(port));
        serverChannel.configureBlocking(false);
    }

    public void connectToPeer(String host, int peerPort) {
        peerList.add(new InetSocketAddress(host, peerPort));
    }

    public void broadcastMessage(String message) throws Exception {
        for (InetSocketAddress peer : peerList) {
            SocketChannel channel = SocketChannel.open(peer);
            channel.write(java.nio.ByteBuffer.wrap(message.getBytes()));
            channel.close();
        }
    }

    public List<InetSocketAddress> getPeerList() {
        return peerList;
    }

    public int getPort() {
        return port;
    }
}
