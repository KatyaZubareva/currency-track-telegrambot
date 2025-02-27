package com.track.currencybot.currency_track_bot.exception;

public class ServiceException extends Exception{

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
