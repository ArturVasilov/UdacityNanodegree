package ru.arturvasilov.stackexchangeclient.api;

import android.support.annotation.NonNull;

import java.util.List;

import ru.arturvasilov.stackexchangeclient.api.service.QuestionService;
import ru.arturvasilov.stackexchangeclient.api.service.UserInfoService;
import ru.arturvasilov.stackexchangeclient.app.analytics.Analytics;
import ru.arturvasilov.stackexchangeclient.model.content.Question;
import ru.arturvasilov.stackexchangeclient.model.content.User;
import ru.arturvasilov.stackexchangeclient.model.database.QuestionTable;
import ru.arturvasilov.stackexchangeclient.model.database.UserTable;
import ru.arturvasilov.stackexchangeclient.model.response.QuestionResponse;
import ru.arturvasilov.stackexchangeclient.model.response.UserResponse;
import ru.arturvasilov.stackexchangeclient.rx.RxSchedulers;
import ru.arturvasilov.stackexchangeclient.sqlite.SQLite;
import ru.arturvasilov.stackexchangeclient.utils.PreferencesUtils;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class RemoteRepository {

    private final UserInfoService mUserInfoService;
    private final QuestionService mQuestionService;

    public RemoteRepository(@NonNull UserInfoService userInfoService, @NonNull QuestionService questionService) {
        mUserInfoService = userInfoService;
        mQuestionService = questionService;
    }

    @NonNull
    public Observable<User> getCurrentUser() {
        return mUserInfoService.getCurrentUser()
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

    @NonNull
    public Observable<List<Question>> questions() {
        return mQuestionService.questions()
                .compose(ErrorsHandler.handleErrors())
                .map(QuestionResponse::getQuestions)
                .flatMap(questions -> {
                    SQLite.get().delete(QuestionTable.TABLE)
                            .where(QuestionTable.TAG + "=?")
                            .whereArgs(new String[]{""})
                            .execute();
                    SQLite.get().insert(QuestionTable.TABLE).insert(questions);
                    return Observable.just(questions);
                })
                .compose(RxSchedulers.async());
    }

    @NonNull
    public Observable<List<Question>> questions(@NonNull String tag) {
        return mQuestionService.questions(tag)
                .compose(ErrorsHandler.handleErrors())
                .map(QuestionResponse::getQuestions)
                .flatMap(Observable::from)
                .map(question -> {
                    question.setTag(tag);
                    return question;
                })
                .toList()
                .flatMap(questions -> {
                    SQLite.get().delete(QuestionTable.TABLE)
                            .where(QuestionTable.TAG + "=?")
                            .whereArgs(new String[]{tag})
                            .execute();
                    SQLite.get().insert(QuestionTable.TABLE).insert(questions);
                    return Observable.just(questions);
                })
                .compose(RxSchedulers.async());
    }
}

