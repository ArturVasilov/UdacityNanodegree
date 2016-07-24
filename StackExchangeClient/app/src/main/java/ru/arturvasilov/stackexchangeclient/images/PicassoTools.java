package ru.arturvasilov.stackexchangeclient.images;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

/**
 * @author Artur Vasilov
 */
public final class PicassoTools {

    private static final int MEMORY_CACHE_SIZE = 1024 * 1024 * 10;
    private static final int DISK_CACHE_SIZE = 1024 * 1024 * 50;

    private PicassoTools() {
    }

    public static void setup(@NonNull Context context) {
        Picasso picasso = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(context, DISK_CACHE_SIZE))
                .memoryCache(new LruCache(MEMORY_CACHE_SIZE))
                .build();
        Picasso.setSingletonInstance(picasso);
    }
}
