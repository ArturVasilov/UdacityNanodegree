package ru.arturvasilov.stackexchangeclient.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * @author Artur Vasilov
 */
public final class Env {

    private Env() {
    }

    public static void browseUrl(@NonNull Context context, @NonNull String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }

    public static void logout() {
        //TODO : clear all data: Hawk, database, logout from site
    }

}
