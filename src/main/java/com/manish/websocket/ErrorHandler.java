package com.manish.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorHandler {
    private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    public static void handleError(String error) {
        logger.error("An error occurred: " + error);
    }
}
