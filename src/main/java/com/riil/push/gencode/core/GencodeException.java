package com.riil.push.gencode.core;

/**
 * Created by sms on 2016/1/19.
 */
public class GencodeException extends RuntimeException {
    public GencodeException() {
        super();
    }

    public GencodeException(String message) {
        super(message);
    }

    public GencodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public GencodeException(Throwable cause) {
        super(cause);
    }
}
