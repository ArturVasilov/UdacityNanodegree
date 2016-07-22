package ru.arturvasilov.stackexchangeclient.api;

import android.support.annotation.NonNull;

import rx.Observable;

/**
 * @author Artur Vasilov
 */
public final class ErrorsHandler {

    private ErrorsHandler() {
    }

    @NonNull
    public static <T> Observable.Transformer<T, T> handleErrors() {
        return observable -> observable;
    }

}
