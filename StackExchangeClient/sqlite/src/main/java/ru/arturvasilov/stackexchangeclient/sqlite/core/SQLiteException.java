package ru.arturvasilov.stackexchangeclient.sqlite.core;

/**
 * @author Artur Vasilov
 */
public class SQLiteException extends RuntimeException {

    public SQLiteException(String detailMessage) {
        super(detailMessage);
    }
}
