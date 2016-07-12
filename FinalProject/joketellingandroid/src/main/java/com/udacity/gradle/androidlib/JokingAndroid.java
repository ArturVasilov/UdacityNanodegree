package com.udacity.gradle.androidlib;

import android.app.Activity;
import android.support.annotation.NonNull;

/**
 * @author Artur Vasilov
 */
public final class JokingAndroid {

    private JokingAndroid() {
    }

    public static void tellJoke(@NonNull Activity activity, @NonNull String joke) {
        JokesActivity.start(activity, joke);
    }
}
