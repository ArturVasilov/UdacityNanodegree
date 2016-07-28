package ru.arturvasilov.stackexchangeclient;

import android.app.Application;
import android.support.annotation.NonNull;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import ru.arturvasilov.stackexchangeclient.api.RepositoryProvider;
import ru.arturvasilov.stackexchangeclient.app.analytics.Analytics;
import ru.arturvasilov.stackexchangeclient.app.analytics.EventTags;
import ru.arturvasilov.stackexchangeclient.data.keyvalue.HawkStorage;
import ru.arturvasilov.stackexchangeclient.sqlite.SQLite;
import ru.arturvasilov.stackexchangeclient.utils.PicassoUtils;

/**
 * @author Artur Vasilov
 */
public class AppDelegate extends Application {

    private static AppDelegate sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        Fabric.with(this, new Crashlytics());
        Analytics.init(this);
        Analytics.buildEvent().log(EventTags.APP_STARTED);

        SQLite.initialize(this);
        RepositoryProvider.setKeyValueStorage(new HawkStorage(this));

        PicassoUtils.setup(this);
    }

    @NonNull
    public static AppDelegate getAppContext() {
        return sInstance;
    }
}
