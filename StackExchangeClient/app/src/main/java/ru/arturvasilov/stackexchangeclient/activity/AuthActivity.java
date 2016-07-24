package ru.arturvasilov.stackexchangeclient.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.arturvasilov.stackexchangeclient.R;
import ru.arturvasilov.stackexchangeclient.presenter.AuthPresenter;
import ru.arturvasilov.stackexchangeclient.router.AuthRouter;
import ru.arturvasilov.stackexchangeclient.utils.TextUtils;
import ru.arturvasilov.stackexchangeclient.utils.Views;
import ru.arturvasilov.stackexchangeclient.view.AuthView;

/**
 * @author Artur Vasilov
 */
public class AuthActivity extends AppCompatActivity implements AuthView {

    private AuthPresenter mPresenter;
    private AuthRouter mRouter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_auth);

        Views.findById(this, R.id.signInButton).setOnClickListener(view -> mPresenter.onLoginButtonClick());

        mPresenter = new AuthPresenter(this);
        mRouter = new AuthRouter(this);

        if (TextUtils.equals(Intent.ACTION_VIEW, getIntent().getAction())) {
            mPresenter.onSuccessUrl(getIntent().getData().toString());
            return;
        }

        mPresenter.init();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (TextUtils.equals(Intent.ACTION_VIEW, intent.getAction())) {
            Uri data = intent.getData();
            mPresenter.onSuccessUrl(data.toString());
        }
    }

    @Override
    public void showAuth(@NonNull String url) {
        mRouter.startAuth(url);
    }

    @Override
    public void showWalkthrough() {
        mRouter.startWalkthrough();
    }

    @Override
    public void showMainScreen() {
        mRouter.startMainScreen();
    }

    @Override
    public void close() {
        onBackPressed();
    }
}
