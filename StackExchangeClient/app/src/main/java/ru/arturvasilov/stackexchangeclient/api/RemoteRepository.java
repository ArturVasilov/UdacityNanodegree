package ru.arturvasilov.stackexchangeclient.api;

import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.arturvasilov.stackexchangeclient.api.service.QuestionService;
import ru.arturvasilov.stackexchangeclient.api.service.TagsService;
import ru.arturvasilov.stackexchangeclient.api.service.UserInfoService;
import ru.arturvasilov.stackexchangeclient.app.analytics.Analytics;
import ru.arturvasilov.stackexchangeclient.model.content.Question;
import ru.arturvasilov.stackexchangeclient.model.content.Tag;
import ru.arturvasilov.stackexchangeclient.model.content.User;
import ru.arturvasilov.stackexchangeclient.model.database.QuestionTable;
import ru.arturvasilov.stackexchangeclient.model.database.UserTable;
import ru.arturvasilov.stackexchangeclient.model.response.QuestionResponse;
import ru.arturvasilov.stackexchangeclient.model.response.TagsResponse;
import ru.arturvasilov.stackexchangeclient.model.response.UserResponse;
import ru.arturvasilov.stackexchangeclient.rx.RxSchedulers;
import ru.arturvasilov.stackexchangeclient.sqlite.SQLite;
import ru.arturvasilov.stackexchangeclient.utils.PreferencesUtils;
import ru.arturvasilov.stackexchangeclient.utils.TextUtils;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class RemoteRepository {

    private final UserInfoService mUserInfoService;
    private final QuestionService mQuestionService;
    private final TagsService mTagsService;

    public RemoteRepository(@NonNull UserInfoService userInfoService, @NonNull QuestionService questionService,
                            @NonNull TagsService tagsService) {
        mUserInfoService = userInfoService;
        mQuestionService = questionService;
        mTagsService = tagsService;
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
    public Observable<List<Question>> questions(@NonNull String tag) {
        Observable<QuestionResponse> questionsObservable;
        if (TextUtils.equals(ApiConstants.TAG_ALL, tag)) {
            questionsObservable = mQuestionService.questions();
        } else if (TextUtils.equals(ApiConstants.TAG_MY_QUESTIONS, tag)) {
            questionsObservable = mQuestionService.myQuestions();
        } else {
            questionsObservable = mQuestionService.questions(tag);
        }
        return questionsObservable
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

    @NonNull
    public Observable<List<Tag>> searchTags(@NonNull @Query("inname") String search) {
        return mTagsService.searchTags(search)
                .map(TagsResponse::getTags)
                .compose(RxSchedulers.async());
    }
}

