package ru.arturvasilov.stackexchangeclient.view;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import java.util.List;

import ru.arturvasilov.stackexchangeclient.model.content.Tag;

/**
 * @author Artur Vasilov
 */
public interface TagsView extends EmptyListView {

    void clearText();

    void setEmptyText(@StringRes int textId);

    void hideAllElements();

    void showTags(@NonNull List<Tag> tags);

    void showClearButton();

    void notifyChanged();
}
