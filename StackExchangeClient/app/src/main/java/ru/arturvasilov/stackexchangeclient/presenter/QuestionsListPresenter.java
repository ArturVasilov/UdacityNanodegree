package ru.arturvasilov.stackexchangeclient.presenter;

import android.app.LoaderManager;
import android.content.Context;
import android.support.annotation.NonNull;

import ru.arturvasilov.stackexchangeclient.api.RepositoryProvider;
import ru.arturvasilov.stackexchangeclient.rx.StubAction;
import ru.arturvasilov.stackexchangeclient.rx.rxloader.RxLoader;
import ru.arturvasilov.stackexchangeclient.view.QuestionsListView;

/**
 * @author Artur Vasilov
 */
public class QuestionsListPresenter {

    private final Context mContext;
    private final LoaderManager mLm;

    private final QuestionsListView mView;

    private final String mTag;

    public QuestionsListPresenter(@NonNull Context context, @NonNull LoaderManager lm,
                                  @NonNull QuestionsListView view, @NonNull String tag) {
        mContext = context;
        mLm = lm;
        mView = view;
        mTag = tag;
    }

    public void init() {
        RepositoryProvider.provideLocalRepository()
                .questions(mTag)
                .subscribe(mView::showQuestions, new StubAction<>());

        RxLoader.create(mContext, mLm, mTag.hashCode(),
                RepositoryProvider.provideRemoteRepository().questions(mTag))
                .init(mView::showQuestions, new StubAction<>());
    }
}
