package ru.arturvasilov.stackexchangeclient.router;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import ru.arturvasilov.stackexchangeclient.activity.MainActivity;
import ru.arturvasilov.stackexchangeclient.activity.WalkthroughActivity;

/**
 * @author Artur Vasilov
 */
public class AuthRouter {

    private final WeakReference<Activity> mActivityReference;

    public AuthRouter(@NonNull Activity activity) {
        mActivityReference = new WeakReference<>(activity);
    }

    public void startAuth(@NonNull String url) {
        Activity activity = mActivityReference.get();
        if (activity != null) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
    }

    public void startWalkthrough() {
        Activity activity = mActivityReference.get();
        if (activity != null) {
            activity.startActivity(new Intent(activity, WalkthroughActivity.class));
        }
    }

    public void startMainScreen() {
        Activity activity = mActivityReference.get();
        if (activity != null) {
            activity.startActivity(new Intent(activity, MainActivity.class));
        }
    }
}
