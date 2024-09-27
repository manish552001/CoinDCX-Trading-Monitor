package com.manish.websocket;

import java.util.Scanner;

public class TradingApp {
    private static double currentMarketPrice = 45000.0; // Default value

    public static void main(String[] args) {
        try {
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);

            // Initialize WebSocket client
            CoinDCXWebSocketClient client = new CoinDCXWebSocketClient("wss://stream.coindcx.com");
            client.connect();

            System.out.print("Enter trigger price for buy order: ");
            double buyTriggerPrice = scanner.nextDouble();

            System.out.print("Enter trigger price for sell order: ");
            double sellTriggerPrice = scanner.nextDouble();

            // Flags to track if any orders are prepared
            boolean isBuyOrderPrepared = false;
            boolean isSellOrderPrepared = false;

            // Create buy order if the market price is less than or equal to the trigger price
            if (currentMarketPrice <= buyTriggerPrice) {
                PayloadPreparation buyOrder = PayloadPreparation.createBuyOrder("BTCUSDT", buyTriggerPrice, currentMarketPrice);
                System.out.println("Prepared Buy Order Payload: " + buyOrder.preparePayload());
                isBuyOrderPrepared = true;
            }

            // Create sell order if the market price is greater than or equal to the trigger price
            if (currentMarketPrice >= sellTriggerPrice) {
                PayloadPreparation sellOrder = PayloadPreparation.createSellOrder("BTCUSDT", sellTriggerPrice, currentMarketPrice);
                System.out.println("Prepared Sell Order Payload: " + sellOrder.preparePayload());
                isSellOrderPrepared = true; 
            }

            // If neither buy nor sell order is prepared, display "No order prepared."
            if (!isBuyOrderPrepared && !isSellOrderPrepared) {
                System.out.println("No order prepared.");
            }

        } catch (Exception e) {
            ErrorHandler.handleError("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void updateMarketPrice(double newPrice) {
        currentMarketPrice = newPrice;
    }
}
