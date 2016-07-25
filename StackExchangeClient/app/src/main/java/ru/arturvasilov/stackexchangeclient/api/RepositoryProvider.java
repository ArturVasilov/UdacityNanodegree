package ru.arturvasilov.stackexchangeclient.api;

import android.support.annotation.NonNull;

/**
 * @author Artur Vasilov
 */
public final class RepositoryProvider {

    private static RemoteRepository sRemoteRepository;
    private static LocalRepository sLocalRepository;

    private RepositoryProvider() {
    }

    @NonNull
    public static RemoteRepository provideRemoteRepository() {
        return sRemoteRepository;
    }

    @NonNull
    public static LocalRepository provideLocalRepository() {
        return sLocalRepository;
    }

    public static void setRemoteRepository(@NonNull RemoteRepository remoteRepository) {
        sRemoteRepository = remoteRepository;
    }

    public static void setLocalRepository(@NonNull LocalRepository localRepository) {
        sLocalRepository = localRepository;
    }
}
