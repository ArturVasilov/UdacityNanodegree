package ru.arturvasilov.stackexchangeclient.app;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Artur Vasilov
 */
public final class GsonHolder {

    private GsonHolder() {
    }

    @NonNull
    public static Gson getGson() {
        return Holder.GSON;
    }

    @NonNull
    public static <T> String listToString(@NonNull List<T> list) {
        Type type = new TypeToken<List<T>>(){}.getType();
        return Holder.GSON.toJson(list, type);
    }

    @NonNull
    public static <T> List<T> listFromString(@NonNull String json) {
        Type type = new TypeToken<List<T>>(){}.getType();
        return Holder.GSON.fromJson(json, type);
    }

    public static final class Holder {
        private static final Gson GSON = new Gson();
    }

}