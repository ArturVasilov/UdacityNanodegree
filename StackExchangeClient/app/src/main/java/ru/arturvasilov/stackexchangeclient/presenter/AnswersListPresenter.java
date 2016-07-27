package ru.arturvasilov.stackexchangeclient.presenter;

import android.app.LoaderManager;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Locale;

import ru.arturvasilov.stackexchangeclient.R;
import ru.arturvasilov.stackexchangeclient.api.RepositoryProvider;
import ru.arturvasilov.stackexchangeclient.model.content.Answer;
import ru.arturvasilov.stackexchangeclient.model.content.User;
import ru.arturvasilov.stackexchangeclient.view.AnswersListView;
import ru.arturvasilov.stackexchangeclient.view.ErrorView;
import ru.arturvasilov.stackexchangeclient.view.LoadingView;

/**
 * @author Artur Vasilov
 */
public class AnswersListPresenter {

    private static final String QUESTION_LINK_FORMAT = "http://stackoverflow.com/a/%1$d/%2$d";

    private final Context mContext;
    private final LoaderManager mLm;

    private final AnswersListView mView;
    private final LoadingView mLoadingView;
    private final ErrorView mErrorView;

    private final User mUser;

    public AnswersListPresenter(@NonNull Context context, @NonNull LoaderManager lm, @NonNull AnswersListView view,
                                @NonNull LoadingView loadingView, @NonNull ErrorView errorView, @NonNull User user) {
        mContext = context;
        mLm = lm;
        mView = view;
        mLoadingView = loadingView;
        mErrorView = errorView;
        mUser = user;
    }

    public void init() {
        mView.setEmptyText(R.string.no_answers);
        RepositoryProvider.provideRemoteRepository()
                .answers(mUser.getUserId())
                .subscribe(mView::showAnswers);
    }

    public void onItemClick(@NonNull Answer answer) {
        String url = String.format(Locale.getDefault(), QUESTION_LINK_FORMAT,
                answer.getQuestionId(), answer.getAnswerId());
        mView.showUrl(url);
    }
}
