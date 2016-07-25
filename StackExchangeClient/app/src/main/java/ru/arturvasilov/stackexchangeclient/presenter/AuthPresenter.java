package ru.arturvasilov.stackexchangeclient.presenter;

import android.support.annotation.NonNull;

import ru.arturvasilov.stackexchangeclient.BuildConfig;
import ru.arturvasilov.stackexchangeclient.api.ApiFactory;
import ru.arturvasilov.stackexchangeclient.api.LocalRepository;
import ru.arturvasilov.stackexchangeclient.api.RepositoryProvider;
import ru.arturvasilov.stackexchangeclient.api.StackRepository;
import ru.arturvasilov.stackexchangeclient.api.RemoteRepository;
import ru.arturvasilov.stackexchangeclient.utils.PreferencesUtils;
import ru.arturvasilov.stackexchangeclient.utils.TextUtils;
import ru.arturvasilov.stackexchangeclient.view.AuthView;

/**
 * @author Artur Vasilov
 */
public class AuthPresenter {

    private static final String OUATH_URL = "https://stackexchange.com/oauth/dialog?client_id=%s&" +
            "scope=%s&redirect_uri=%s";

    private static final String SCOPE = "read_inbox,no_expiry,write_access,private_info";
    private static final String REDIRECT_URI = "https://stackexchange.com/oauth/login_success";

    private final AuthView mView;

    public AuthPresenter(@NonNull AuthView view) {
        mView = view;
    }

    public void init() {
        PreferencesUtils.getAccessToken()
                .map(TextUtils::isEmpty)
                .map(value -> !value)
                .subscribe(this::onAuth);
    }

    public void onSuccessUrl(@NonNull String url) {
        String accessToken = url.split("#")[1].split("=")[1];
        PreferencesUtils.saveAccessToken(accessToken).subscribe(this::onAuth);
    }

    public void onLoginButtonClick() {
        mView.showAuth(buildAuthUrl());
    }

    private void onAuth(boolean success) {
        if (success) {
            initApi();
            if (PreferencesUtils.isWalkthroughPassed()) {
                mView.showMainScreen();
            } else {
                mView.showWalkthrough();
            }
            mView.close();
        }
    }

    @NonNull
    private String buildAuthUrl() {
        return String.format(OUATH_URL, BuildConfig.CLIENT_ID, SCOPE, REDIRECT_URI);
    }

    private void initApi() {
        StackRepository repository = new RemoteRepository(ApiFactory.getUserInfoService());
        RepositoryProvider.setRemoteRepository(repository);
        RepositoryProvider.setLocalRepository(new LocalRepository());
    }
}
