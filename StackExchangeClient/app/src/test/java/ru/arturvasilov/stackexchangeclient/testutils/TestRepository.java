package ru.arturvasilov.stackexchangeclient.testutils;

import android.support.annotation.NonNull;

import ru.arturvasilov.stackexchangeclient.api.StackRepository;
import ru.arturvasilov.stackexchangeclient.api.constants.Site;
import ru.arturvasilov.stackexchangeclient.model.content.User;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class TestRepository implements StackRepository {

    @NonNull
    @Override
    public Observable<User> getCurrentUser(@Site String site) {
        User user = new User();
        user.setUserId(932123);
        user.setAge(19);
        user.setName("John");
        user.setReputation(2985);
        user.setLink("http://stackoverflow.com/users/3637200/vasilov-artur");
        user.setProfileImage("https://i.stack.imgur.com/EJNBv.jpg?s=512&g=1");

        return Observable.just(new User());
    }
}
