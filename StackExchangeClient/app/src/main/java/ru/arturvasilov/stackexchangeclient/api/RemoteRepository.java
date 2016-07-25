package ru.arturvasilov.stackexchangeclient.api;

import android.support.annotation.NonNull;

import ru.arturvasilov.stackexchangeclient.api.constants.Site;
import ru.arturvasilov.stackexchangeclient.app.analytics.Analytics;
import ru.arturvasilov.stackexchangeclient.model.content.User;
import ru.arturvasilov.stackexchangeclient.model.database.UserTable;
import ru.arturvasilov.stackexchangeclient.model.response.UserResponse;
import ru.arturvasilov.stackexchangeclient.rx.RxSchedulers;
import ru.arturvasilov.stackexchangeclient.sqlite.SQLite;
import ru.arturvasilov.stackexchangeclient.utils.PreferencesUtils;
import rx.Observable;
import rx.functions.Func1;

/**
 * @author Artur Vasilov
 */
public class RemoteRepository implements StackRepository {

    private final UserInfoService mService;

    public RemoteRepository(@NonNull UserInfoService service) {
        mService = service;
    }

    @NonNull
    @Override
    public Observable<User> getCurrentUser(@Site String site) {
        return mService.getCurrentUser(site)
                .compose(ErrorsHandler.handleErrors())
                .map(UserResponse::getUsers)
                .map(users -> users.get(0))
                .flatMap(user -> {
                    SQLite.get().insert(UserTable.TABLE).insert(user);
                    PreferencesUtils.saveUserId(user.getUserId());
                    Analytics.setCurrentUser(user);
                    return Observable.just(user);
                })
                .compose(RxSchedulers.async());
    }
}

