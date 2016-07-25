package ru.arturvasilov.stackexchangeclient.testutils;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import ru.arturvasilov.stackexchangeclient.api.RemoteRepository;
import ru.arturvasilov.stackexchangeclient.api.constants.Site;
import ru.arturvasilov.stackexchangeclient.api.service.QuestionService;
import ru.arturvasilov.stackexchangeclient.api.service.UserInfoService;
import ru.arturvasilov.stackexchangeclient.model.content.Question;
import ru.arturvasilov.stackexchangeclient.model.content.User;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class TestRepository extends RemoteRepository {

    public TestRepository(@NonNull UserInfoService userInfoService, @NonNull QuestionService questionService) {
        super(userInfoService, questionService);
    }

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

    @NonNull
    @Override
    public Observable<List<Question>> questions(@NonNull @Site String site) {
        return Observable.just(new ArrayList<>());
    }

    @NonNull
    @Override
    public Observable<List<Question>> questions(@NonNull @Site String site, @NonNull String tag) {
        return Observable.just(new ArrayList<>());
    }
}
