package arturvasilov.udacity.nanodegree.popularmoviesdatabinding;

import android.app.Application;
import android.support.annotation.NonNull;

/**
 * @author Artur Vasilov
 */
public class AppDelegate extends Application {

    private static AppDelegate sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    @NonNull
    public static AppDelegate getAppContext() {
        return sInstance;
    }
}
