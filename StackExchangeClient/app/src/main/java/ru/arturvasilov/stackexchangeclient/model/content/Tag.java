package ru.arturvasilov.stackexchangeclient.model.content;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * @author Artur Vasilov
 */
public class Tag {

    @SerializedName("name")
    private final String mName;

    private boolean mIsFavourite;

    public Tag(@NonNull String name, boolean isFavourite) {
        mName = name;
        mIsFavourite = isFavourite;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public boolean isFavourite() {
        return mIsFavourite;
    }

    public void setFavourite(boolean favourite) {
        mIsFavourite = favourite;
    }
}
