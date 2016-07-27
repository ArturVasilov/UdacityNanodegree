package ru.arturvasilov.stackexchangeclient.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import ru.arturvasilov.stackexchangeclient.R;
import ru.arturvasilov.stackexchangeclient.api.ApiConstants;
import ru.arturvasilov.stackexchangeclient.api.LocalRepository;
import ru.arturvasilov.stackexchangeclient.api.RepositoryProvider;
import ru.arturvasilov.stackexchangeclient.model.content.User;
import ru.arturvasilov.stackexchangeclient.rx.RxSchedulers;
import ru.arturvasilov.stackexchangeclient.rx.StubAction;
import ru.arturvasilov.stackexchangeclient.utils.TextUtils;
import ru.arturvasilov.stackexchangeclient.view.MainView;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class MainPresenter {

    private final Context mContext;
    private final MainView mView;

    private User mCurrentUser;

    public MainPresenter(@NonNull Context context, @NonNull MainView view) {
        mContext = context;
        mView = view;
    }

    public void init() {
        LocalRepository repository = RepositoryProvider.provideLocalRepository();

        repository.getCurrentUser()
                .subscribe(user -> {
                    mCurrentUser = user;
                    mView.showUserImage(user.getProfileImage());
                    mView.showUserName(user.getName());
                }, new StubAction<>());

        Observable.zip(repository.questions(ApiConstants.TAG_MY_QUESTIONS), repository.tags(),
                (myQuestions, tags) -> {
                    if (tags == null) {
                        tags = new ArrayList<>();
                    }
                    if (myQuestions != null && !myQuestions.isEmpty()) {
                        tags.add(0, ApiConstants.TAG_MY_QUESTIONS);
                    }
                    tags.add(0, ApiConstants.TAG_ALL);
                    return tags;
                })
                .observeOn(RxSchedulers.main())
                .doOnNext(tags -> {
                    if (tags.size() < 1) {
                        mView.hideTabLayout();
                    }
                })
                .flatMap(Observable::from)
                .doOnNext(tag -> {
                    if (TextUtils.equals(ApiConstants.TAG_ALL, tag)) {
                        mView.addTab(mContext.getString(R.string.all));
                    } else if (TextUtils.equals(ApiConstants.TAG_MY_QUESTIONS, tag)) {
                        mView.addTab(mContext.getString(R.string.my));
                    } else {
                        mView.addTab(tag);
                    }
                })
                .toList()
                .subscribe(mView::showTags, new StubAction<>());
    }

    public void onProfileSelected() {
        mView.openProfile(mCurrentUser);
    }
}
