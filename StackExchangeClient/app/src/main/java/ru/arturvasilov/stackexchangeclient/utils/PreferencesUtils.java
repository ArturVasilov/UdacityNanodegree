package ru.arturvasilov.stackexchangeclient.utils;

import android.support.annotation.NonNull;

import com.orhanobut.hawk.Hawk;

import ru.arturvasilov.stackexchangeclient.model.content.User;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public final class PreferencesUtils {

    private static final String KEY_ACCESS_TOKEN = "key_access_token";
    private static final String KEY_WALKTHROUGH_PASSED = "key_walkthrough_passed";
    private static final String KEY_USER_ID = "key_user_id";

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

    @NonNull
    public static String obtainAccessToken() {
        return Hawk.get(KEY_ACCESS_TOKEN);
    }

    public static void saveWalkthroughPassed() {
        Hawk.put(KEY_WALKTHROUGH_PASSED, true);
    }

    public static boolean isWalkthroughPassed() {
        return Hawk.get(KEY_WALKTHROUGH_PASSED, false);
    }

    public static void saveUserId(int userId) {
        Hawk.put(KEY_USER_ID, userId);
    }

    @NonNull
    public static Observable<Integer> getCurrentUserId() {
        return Hawk.getObservable(KEY_USER_ID, -1);
    }

}
