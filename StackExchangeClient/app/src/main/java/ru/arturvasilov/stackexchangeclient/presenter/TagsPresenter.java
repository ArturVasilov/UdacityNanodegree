package ru.arturvasilov.stackexchangeclient.presenter;

import android.app.LoaderManager;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import ru.arturvasilov.stackexchangeclient.R;
import ru.arturvasilov.stackexchangeclient.api.RepositoryProvider;
import ru.arturvasilov.stackexchangeclient.model.content.Tag;
import ru.arturvasilov.stackexchangeclient.model.database.TagTable;
import ru.arturvasilov.stackexchangeclient.rx.StubAction;
import ru.arturvasilov.stackexchangeclient.sqlite.SQLite;
import ru.arturvasilov.stackexchangeclient.utils.TextUtils;
import ru.arturvasilov.stackexchangeclient.view.TagsView;
import rx.Observable;
import rx.functions.Func2;

/**
 * @author Artur Vasilov
 */
public class TagsPresenter {

    private final Context mContext;
    private final LoaderManager mLm;
    private final TagsView mView;

    private final List<Tag> mFavouriteTags;
    private final List<Tag> mFoundTags;

    private boolean mIsFavouriteShown;

    public TagsPresenter(@NonNull Context context, @NonNull LoaderManager lm, @NonNull TagsView view) {
        mContext = context;
        mLm = lm;
        mView = view;

        mFavouriteTags = new ArrayList<>();
        mFoundTags = new ArrayList<>();
    }

    public void init() {
        mView.setEmptyText(R.string.no_tags_found);

        RepositoryProvider.provideLocalRepository()
                .tags()
                .flatMap(Observable::from)
                .toSortedList()
                .flatMap(Observable::from)
                .map(tag -> new Tag(tag, true))
                .toList()
                .subscribe(tags -> {
                    if (tags.isEmpty()) {
                        mView.hideAllElements();
                    } else {
                        mFavouriteTags.addAll(tags);
                        mView.showTags(mFavouriteTags);
                        mIsFavouriteShown = true;
                    }
                }, new StubAction<>());
    }

    public void onClearButtonClick() {
        mView.clearText();
        if (mFavouriteTags.isEmpty()) {
            mView.hideAllElements();
        } else {
            mView.showTags(mFavouriteTags);
            mIsFavouriteShown = true;
        }
    }

    public void onInput(@NonNull String input) {
        if (TextUtils.isEmpty(input)) {
            if (mFavouriteTags.isEmpty()) {
                mView.hideAllElements();
            } else {
                mView.showTags(mFavouriteTags);
                mIsFavouriteShown = true;
            }
            return;
        }
        mView.showClearButton();

        final String search = input.toLowerCase();
        RepositoryProvider.provideRemoteRepository()
                .searchTags(search)
                .flatMap(Observable::from)
                .map(tag -> {
                    for (Tag favourite : mFavouriteTags) {
                        if (TextUtils.equals(favourite.getName(), tag.getName())) {
                            tag.setFavourite(true);
                            return tag;
                        }
                    }
                    return tag;
                })
                .toSortedList((tag, tag2) -> {
                    return tag.getName().compareTo(tag2.getName());
                })
                .subscribe(tags -> {
                    mFoundTags.clear();
                    mFoundTags.addAll(tags);
                    if (mFoundTags.isEmpty()) {
                        mView.showEmptyListView();
                    } else {
                        mView.hideEmptyListView();
                        mView.showTags(mFoundTags);
                        mIsFavouriteShown = false;
                    }
                }, throwable -> mView.showEmptyListView());
    }

    public void onFavouriteClick(int position) {
        Tag tag;
        if (mIsFavouriteShown) {
            tag = mFavouriteTags.get(position);
        } else {
            tag = mFoundTags.get(position);
        }

        if (tag.isFavourite()) {
            SQLite.get().delete(TagTable.TABLE)
                    .where(TagTable.TAG + "=?")
                    .whereArgs(new String[]{tag.getName()})
                    .execute();
            tag.setFavourite(false);
        } else {
            SQLite.get().insert(TagTable.TABLE).insert(tag.getName());
            tag.setFavourite(true);
        }
        mView.notifyChanged();
    }
}
