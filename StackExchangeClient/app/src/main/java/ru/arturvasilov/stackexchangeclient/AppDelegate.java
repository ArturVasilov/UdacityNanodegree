package ru.arturvasilov.stackexchangeclient;

import android.app.Application;
import android.support.annotation.NonNull;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;

import ru.arturvasilov.stackexchangeclient.api.ApiFactory;
import ru.arturvasilov.stackexchangeclient.api.RepositoryProvider;
import ru.arturvasilov.stackexchangeclient.api.StackRepository;
import ru.arturvasilov.stackexchangeclient.images.PicassoTools;

/**
 * @author Artur Vasilov
 */
public class AppDelegate extends Application {

    private static AppDelegate sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        PicassoTools.setup(this);

        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSharedPrefStorage(this))
                .setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE)
                .build();
    }

    @NonNull
    public static AppDelegate getAppContext() {
        return sInstance;
    }
}
