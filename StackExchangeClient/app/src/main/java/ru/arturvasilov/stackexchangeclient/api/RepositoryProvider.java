package ru.arturvasilov.stackexchangeclient.api;

import android.support.annotation.NonNull;

/**
 * @author Artur Vasilov
 */
public final class RepositoryProvider {

    private static StackRepository sRemoteRepository;
    private static StackRepository sLocalRepository;

    private RepositoryProvider() {
    }

    @NonNull
    public static StackRepository provideRemoteRepository() {
        return sRemoteRepository;
    }

    @NonNull
    public static StackRepository provideLocalRepository() {
        return sLocalRepository;
    }

    public static void setRemoteRepository(@NonNull StackRepository remoteRepository) {
        sRemoteRepository = remoteRepository;
    }

    public static void setLocalRepository(@NonNull StackRepository localRepository) {
        sLocalRepository = localRepository;
    }
}
