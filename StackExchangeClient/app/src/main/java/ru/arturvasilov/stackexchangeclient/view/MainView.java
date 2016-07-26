package ru.arturvasilov.stackexchangeclient.view;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import java.util.List;

/**
 * @author Artur Vasilov
 */
public interface MainView {

    void showUserImage(@NonNull String imageUrl);

    void showUserName(@NonNull String name);

    void addTab(@NonNull String tabTitle);

    void showTags(@NonNull List<String> tags);

    void hideTabLayout();

}
