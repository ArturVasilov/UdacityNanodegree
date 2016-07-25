package ru.arturvasilov.stackexchangeclient.view;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

/**
 * @author Artur Vasilov
 */
public interface MainView {

    void showUserImage(@NonNull String imageUrl);

    void showUserName(@NonNull String name);

    void addTab(@StringRes int tabTitleResId);

}
