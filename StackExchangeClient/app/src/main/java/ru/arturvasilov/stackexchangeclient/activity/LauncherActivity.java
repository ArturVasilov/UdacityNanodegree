package ru.arturvasilov.stackexchangeclient.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import ru.arturvasilov.stackexchangeclient.BuildConfig;
import ru.arturvasilov.stackexchangeclient.utils.PreferencesUtils;
import ru.arturvasilov.stackexchangeclient.R;
import ru.arturvasilov.stackexchangeclient.utils.TextUtils;

public class LauncherActivity extends AppCompatActivity {

    private static final String OUATH_URL = "https://stackexchange.com/oauth/dialog?client_id=%s&" +
            "scope=%s&redirect_uri=%s";

    private static final String SCOPE = "read_inbox,no_expiry,write_access,private_info";
    private static final String REDIRECT_URI = "https://stackexchange.com/oauth/login_success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferencesUtils.getAccessToken()
                .map(TextUtils::isEmpty)
                .map(value -> !value)
                .subscribe(value -> {
                    if (value) {
                        MainActivity.start(LauncherActivity.this);
                    } else {
                        startActivity(new Intent(Intent.ACTION_VIEW, buildOAuthUri()));
                    }
                });
    }

    @NonNull
    private Uri buildOAuthUri() {
        String url = String.format(OUATH_URL, BuildConfig.CLIENT_ID, SCOPE, REDIRECT_URI);
        return Uri.parse(url);
    }
}
