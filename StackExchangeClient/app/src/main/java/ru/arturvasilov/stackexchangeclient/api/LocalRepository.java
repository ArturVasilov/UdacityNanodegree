package ru.arturvasilov.stackexchangeclient.api;

import android.support.annotation.NonNull;

import ru.arturvasilov.stackexchangeclient.AppDelegate;
import ru.arturvasilov.stackexchangeclient.api.constants.Site;
import ru.arturvasilov.stackexchangeclient.model.content.User;
import ru.arturvasilov.stackexchangeclient.model.database.UserTable;
import ru.arturvasilov.stackexchangeclient.rx.RxSchedulers;
import ru.arturvasilov.stackexchangeclient.sqlite.SQLite;
import ru.arturvasilov.stackexchangeclient.utils.PreferencesUtils;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class LocalRepository implements StackRepository {

    private final SQLite mDb;

    public LocalRepository() {
        mDb = SQLite.initialize(AppDelegate.getAppContext());
    }

    @NonNull
    @Override
    public Observable<User> getCurrentUser(@Site String site) {
        return PreferencesUtils.getCurrentUserId()
                .take(1)
                .filter(id -> id > 0)
                .map(String::valueOf)
                .flatMap(id -> mDb.query(UserTable.TABLE)
                        .object()
                        .where(UserTable.USER_ID + "=?")
                        .whereArgs(new String[]{id})
                        .asObservable())
                .compose(RxSchedulers.async());
    }
}
