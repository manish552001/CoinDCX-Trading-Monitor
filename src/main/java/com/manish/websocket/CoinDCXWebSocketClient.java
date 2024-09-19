package com.manish.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
public class CoinDCXWebSocketClient extends WebSocketClient {

    public CoinDCXWebSocketClient(String url) throws URISyntaxException {
        super(new URI(url));
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("WebSocket connection opened.");
        // Send authentication message if required
        String authMessage = "{\"event\": \"auth\", \"api_key\": \"ba6ce9c712f7cb0cde6f2da473cc98b783f91398c7ef318d\"}";
        this.send(authMessage);

        // Send a subscription message
        String subscribeMessage = "{\"event\": \"subscribe\", \"channel\": \"market\", \"pair\": \"BTCUSDT\"}";
        this.send(subscribeMessage);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Connection closed with reason: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("WebSocket error: " + ex.getMessage());
        ex.printStackTrace();
    }
}
