package ru.arturvasilov.stackexchangeclient.api;

import android.support.annotation.NonNull;

import ru.arturvasilov.stackexchangeclient.api.constants.Site;
import ru.arturvasilov.stackexchangeclient.model.User;
import ru.arturvasilov.stackexchangeclient.model.response.UserResponse;
import ru.arturvasilov.stackexchangeclient.rx.RxSchedulers;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class StackRepositoryImpl implements StackRepository {

    private final UserInfoService mService;

    public StackRepositoryImpl(@NonNull UserInfoService service) {
        mService = service;
    }

    @NonNull
    @Override
    public Observable<User> getCurrentUser(@Site String site) {
        return mService.getCurrentUser(site)
                .compose(ErrorsHandler.handleErrors())
                .map(UserResponse::getUsers)
                .map(users -> users.get(0))
                .compose(RxSchedulers.async());
    }
}

