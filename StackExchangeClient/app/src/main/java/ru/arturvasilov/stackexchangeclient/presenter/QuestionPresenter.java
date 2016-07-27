package ru.arturvasilov.stackexchangeclient.presenter;

import android.app.LoaderManager;
import android.content.Context;
import android.support.annotation.NonNull;

import ru.arturvasilov.stackexchangeclient.api.RepositoryProvider;
import ru.arturvasilov.stackexchangeclient.model.content.Question;
import ru.arturvasilov.stackexchangeclient.view.ErrorView;
import ru.arturvasilov.stackexchangeclient.view.LoadingView;
import ru.arturvasilov.stackexchangeclient.view.QuestionView;

/**
 * @author Artur Vasilov
 */
public class QuestionPresenter {

    private final Context mContext;
    private final LoaderManager mLm;

    private final QuestionView mView;
    private final LoadingView mLoadingView;
    private final ErrorView mErrorView;

    private Question mQuestion;

    public QuestionPresenter(@NonNull Context context, @NonNull LoaderManager lm, @NonNull QuestionView view,
                             @NonNull LoadingView loadingView, @NonNull ErrorView errorView, @NonNull Question question) {
        mContext = context;
        mLm = lm;
        mView = view;
        mLoadingView = loadingView;
        mErrorView = errorView;
        mQuestion = question;
    }

    public void init() {
        RepositoryProvider.provideRemoteRepository()
                .questionWithBody(mQuestion.getQuestionId())
                .subscribe(question -> {
                    mQuestion = question;
                    mView.showQuestion(question);
                });

        RepositoryProvider.provideRemoteRepository()
                .questionAnswers(mQuestion.getQuestionId())
                .subscribe(mView::showAnswers);
    }

}
