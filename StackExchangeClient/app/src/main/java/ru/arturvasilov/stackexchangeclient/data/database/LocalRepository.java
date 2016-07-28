package ru.arturvasilov.stackexchangeclient.data.database;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import ru.arturvasilov.stackexchangeclient.api.RepositoryProvider;
import ru.arturvasilov.stackexchangeclient.model.content.Question;
import ru.arturvasilov.stackexchangeclient.model.content.User;
import ru.arturvasilov.stackexchangeclient.rx.RxSchedulers;
import ru.arturvasilov.stackexchangeclient.sqlite.SQLite;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class LocalRepository {

    private final SQLite mDb;

    public LocalRepository(@NonNull Context context) {
        mDb = SQLite.initialize(context);
    }

    @NonNull
    public Observable<User> getCurrentUser() {
        return RepositoryProvider.provideKeyValueStorage().getCurrentUserId()
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

    @NonNull
    public Observable<List<Question>> questions(@NonNull String tag) {
        return mDb.query(QuestionTable.TABLE)
                .all()
                .where(QuestionTable.TAG + "=?")
                .whereArgs(new String[]{tag})
                .asObservable()
                .compose(RxSchedulers.async());
    }

    @NonNull
    public Observable<List<String>> tags() {
        return mDb.query(TagTable.TABLE)
                .all()
                .asObservable()
                .compose(RxSchedulers.async());
    }
}
