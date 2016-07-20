package com.example.xyzreader;

import android.app.Application;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

/**
 * @author Artur Vasilov
 */
public class AppDelegate extends Application {

    private static final int MEMORY_CACHE_SIZE = 1024 * 1024 * 10;
    private static final int DISK_CACHE_SIZE = 1024 * 1024 * 50;

    @Override
    public void onCreate() {
        super.onCreate();

        Picasso picasso = new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(this, DISK_CACHE_SIZE))
                .memoryCache(new LruCache(MEMORY_CACHE_SIZE))
                .build();
        Picasso.setSingletonInstance(picasso);
    }
}
