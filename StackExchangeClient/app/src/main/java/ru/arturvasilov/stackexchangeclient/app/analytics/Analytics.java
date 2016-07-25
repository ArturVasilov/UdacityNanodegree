package ru.arturvasilov.stackexchangeclient.app.analytics;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import com.google.firebase.analytics.FirebaseAnalytics;

import ru.arturvasilov.stackexchangeclient.BuildConfig;
import ru.arturvasilov.stackexchangeclient.model.content.User;

/**
 * @author Artur Vasilov
 */
public class Analytics {

    private static final String APP_VERSION_KEY = "app_version";
    private static final String ANDROID_API_KEY = "android_api";
    private static final String ANDROID_DEVICE_NAME_KEY = "android_device_name";
    private static final String ANDROID_DEVICE_MODEL_KEY = "android_device_model";
    private static final String USER_ID_KEY = "user_id";
    private static final String USER_NAME_KEY = "user_key";

    private static FirebaseAnalytics sAnalytics;

    public static void init(@NonNull Context context) {
        sAnalytics = FirebaseAnalytics.getInstance(context);
        sAnalytics.setUserProperty(APP_VERSION_KEY, BuildConfig.VERSION_NAME);
        sAnalytics.setUserProperty(ANDROID_API_KEY, String.valueOf(Build.VERSION.SDK_INT));
        sAnalytics.setUserProperty(ANDROID_DEVICE_NAME_KEY, Build.MODEL);
        sAnalytics.setUserProperty(ANDROID_DEVICE_MODEL_KEY, Build.PRODUCT);
    }

    public static void setCurrentUser(@NonNull User currentUser) {
        sAnalytics.setUserProperty(USER_ID_KEY, String.valueOf(currentUser.getUserId()));
        sAnalytics.setUserProperty(USER_NAME_KEY, currentUser.getName());
    }

    @NonNull
    public static EventBuilder buildEvent() {
        return new EventBuilder();
    }

    public static class EventBuilder {

        private final Bundle mBundle;

        private EventBuilder() {
            mBundle = new Bundle();
        }

        @NonNull
        public EventBuilder putString(@NonNull @EventKey String key, @NonNull String value) {
            mBundle.putString(key, value);
            return this;
        }

        public void log(@NonNull @EventTag String eventTag) {
            sAnalytics.logEvent(eventTag, mBundle);
        }

    }

    @StringDef({EventTags.APP_STARTED})
    private @interface EventTag {
    }

    @StringDef({})
    private @interface EventKey {
    }

}
