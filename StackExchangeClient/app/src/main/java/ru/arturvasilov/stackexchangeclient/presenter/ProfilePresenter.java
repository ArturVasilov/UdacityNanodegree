package ru.arturvasilov.stackexchangeclient.presenter;

import android.app.LoaderManager;
import android.content.Context;
import android.support.annotation.NonNull;

import ru.arturvasilov.stackexchangeclient.R;
import ru.arturvasilov.stackexchangeclient.api.RepositoryProvider;
import ru.arturvasilov.stackexchangeclient.model.content.User;
import ru.arturvasilov.stackexchangeclient.view.ErrorView;
import ru.arturvasilov.stackexchangeclient.view.LoadingView;
import ru.arturvasilov.stackexchangeclient.view.ProfileView;

/**
 * @author Artur Vasilov
 */
public class ProfilePresenter {

    private static final String LINK_FORMAT = "<a href=\"%1$s\">%2$s</a>";

    private final Context mContext;
    private final LoaderManager mLm;

    private final ProfileView mView;
    private final LoadingView mLoadingView;
    private final ErrorView mErrorView;

    private final User mUser;

    public ProfilePresenter(@NonNull Context context, @NonNull LoaderManager lm, @NonNull ProfileView view,
                            @NonNull LoadingView loadingView, @NonNull ErrorView errorView, @NonNull User user) {
        mContext = context;
        mLm = lm;
        mView = view;
        mLoadingView = loadingView;
        mErrorView = errorView;
        mUser = user;
    }

    public void init() {
        mView.showUserName(mUser.getName());
        String imageLink = mUser.getProfileImage().replace("sz=128", "sz=1024");
        mView.showUserImage(imageLink);
        mView.showReputation(mContext.getString(R.string.reputation, mUser.getReputation()));
        mView.showProfileLink(String.format(LINK_FORMAT, mUser.getLink(), mContext.getString(R.string.profile_text)));

        RepositoryProvider.provideRemoteRepository()
                .badges(mUser.getUserId())
                .subscribe(mView::showBadges);

        RepositoryProvider.provideRemoteRepository()
                .topTags(mUser.getUserId())
                .subscribe(mView::showTopTags);
    }
}
