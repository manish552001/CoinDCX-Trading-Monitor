package com.manish.websocket;



import java.util.Scanner;

public class TradingApp {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // Initialize WebSocket client
            CoinDCXWebSocketClient client = new CoinDCXWebSocketClient("wss://stream.coindcx.com");
            client.connect();

            System.out.print("Enter trigger price for buy order: ");
            double buyTriggerPrice = scanner.nextDouble();

            System.out.print("Enter trigger price for sell order: ");
            double sellTriggerPrice = scanner.nextDouble();

            // Mock current market price
            double currentMarketPrice = 45000.0;

            // Create buy order if the market price is less than or equal to the trigger price
            if (currentMarketPrice <= buyTriggerPrice) {
                PayloadPreparation buyOrder = PayloadPreparation.createBuyOrder("BTCUSDT", buyTriggerPrice, currentMarketPrice);
                System.out.println("Prepared Buy Order Payload: " + buyOrder.preparePayload());
            }

            // Create sell order if the market price is greater than or equal to the trigger price
            if (currentMarketPrice >= sellTriggerPrice) {
                PayloadPreparation sellOrder = PayloadPreparation.createSellOrder("BTCUSDT", sellTriggerPrice, currentMarketPrice);
                System.out.println("Prepared Sell Order Payload: " + sellOrder.preparePayload());
            }

        } catch (Exception e) {
            ErrorHandler.handleError("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
