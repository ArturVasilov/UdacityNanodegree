package ru.arturvasilov.stackexchangeclient.model.response;

import android.support.annotation.NonNull;

import java.io.IOException;

/**
 * @author Artur Vasilov
 */
public class ServerError extends IOException {

    private final ApiError mError;

    public ServerError(@NonNull ApiError error) {
        super(error.getErrorMessage());
        mError = error;
    }

    @NonNull
    public ApiError getError() {
        return mError;
    }
}
