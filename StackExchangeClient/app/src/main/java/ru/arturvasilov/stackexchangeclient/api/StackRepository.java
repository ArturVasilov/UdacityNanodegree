package ru.arturvasilov.stackexchangeclient.api;

import android.support.annotation.NonNull;

import ru.arturvasilov.stackexchangeclient.api.constants.Site;
import ru.arturvasilov.stackexchangeclient.model.content.User;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public interface StackRepository {

    @NonNull
    Observable<User> getCurrentUser(@Site String site);

}
