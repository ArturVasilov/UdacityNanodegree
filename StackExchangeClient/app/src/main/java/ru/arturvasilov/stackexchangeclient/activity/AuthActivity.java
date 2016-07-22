package ru.arturvasilov.stackexchangeclient.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.arturvasilov.stackexchangeclient.BuildConfig;
import ru.arturvasilov.stackexchangeclient.api.ApiFactory;
import ru.arturvasilov.stackexchangeclient.api.RepositoryProvider;
import ru.arturvasilov.stackexchangeclient.api.StackRepository;
import ru.arturvasilov.stackexchangeclient.api.StackRepositoryImpl;
import ru.arturvasilov.stackexchangeclient.utils.PreferencesUtils;
import ru.arturvasilov.stackexchangeclient.utils.TextUtils;

/**
 * @author Artur Vasilov
 */
public class AuthActivity extends AppCompatActivity {

    private static final String OUATH_URL = "https://stackexchange.com/oauth/dialog?client_id=%s&" +
            "scope=%s&redirect_uri=%s";

    private static final String SCOPE = "read_inbox,no_expiry,write_access,private_info";
    private static final String REDIRECT_URI = "https://stackexchange.com/oauth/login_success";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (TextUtils.equals(Intent.ACTION_VIEW, getIntent().getAction())) {
            onNewIntent(getIntent());
            return;
        }

        PreferencesUtils.getAccessToken()
                .map(TextUtils::isEmpty)
                .map(value -> !value)
                .subscribe(value -> {
                    if (value) {
                        initApi();
                        MainActivity.start(AuthActivity.this);
                    } else {
                        startActivity(new Intent(Intent.ACTION_VIEW, buildOAuthUri()));
                    }
                    finish();
                });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (TextUtils.equals(Intent.ACTION_VIEW, intent.getAction())) {
            Uri data = intent.getData();
            String accessToken = data.toString().split("#")[1].split("=")[1];
            PreferencesUtils.saveAccessToken(accessToken)
                    .subscribe(aBoolean -> {
                        initApi();
                        MainActivity.start(AuthActivity.this);
                        finish();
                    });
        }
    }

    @NonNull
    private Uri buildOAuthUri() {
        String url = String.format(OUATH_URL, BuildConfig.CLIENT_ID, SCOPE, REDIRECT_URI);
        return Uri.parse(url);
    }

    private void initApi() {
        StackRepository repository = new StackRepositoryImpl(ApiFactory.getUserInfoService());
        RepositoryProvider.setRepository(repository);
    }
}
