package com.manish.websocket;

public class PayloadPreparation {
    private String orderType;
    private String market;
    private double triggerPrice;
    private double currentPrice;

    public PayloadPreparation(String orderType, String market, double triggerPrice, double currentPrice) {
        this.orderType = orderType;
        this.market = market;
        this.triggerPrice = triggerPrice;
        this.currentPrice = currentPrice;
    }

    public String preparePayload() {
        return String.format("{ \"order_type\": \"%s\", \"market\": \"%s\", \"trigger_price\": %.2f, \"current_price\": %.2f }",
                orderType, market, triggerPrice, currentPrice);
    }

    public static PayloadPreparation createBuyOrder(String market, double triggerPrice, double currentPrice) {
        return new PayloadPreparation("buy", market, triggerPrice, currentPrice);
    }

    public static PayloadPreparation createSellOrder(String market, double triggerPrice, double currentPrice) {
        return new PayloadPreparation("sell", market, triggerPrice, currentPrice);
    }
}
