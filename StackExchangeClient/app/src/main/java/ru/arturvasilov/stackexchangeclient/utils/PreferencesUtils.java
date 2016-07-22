package ru.arturvasilov.stackexchangeclient.utils;

import android.support.annotation.NonNull;

import com.orhanobut.hawk.Hawk;

import rx.Observable;

/**
 * @author Artur Vasilov
 */
public final class PreferencesUtils {

    public static final String KEY_ACCESS_TOKEN = "key_access_token";

    private PreferencesUtils() {
    }

    @NonNull
    public static Observable<Boolean> saveAccessToken(@NonNull String token) {
        return Hawk.putObservable(KEY_ACCESS_TOKEN, token);
    }

    @NonNull
    public static Observable<String> getAccessToken() {
        return Hawk.getObservable(KEY_ACCESS_TOKEN);
    }

}
