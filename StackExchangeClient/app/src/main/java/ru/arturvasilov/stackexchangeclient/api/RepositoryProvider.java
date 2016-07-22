package ru.arturvasilov.stackexchangeclient.api;

import android.support.annotation.NonNull;

/**
 * @author Artur Vasilov
 */
public final class RepositoryProvider {

    private static StackRepository sRepository;

    private RepositoryProvider() {
    }

    @NonNull
    public static StackRepository getRepository() {
        return sRepository;
    }

    public static void setRepository(@NonNull StackRepository repository) {
        sRepository = repository;
    }
}
