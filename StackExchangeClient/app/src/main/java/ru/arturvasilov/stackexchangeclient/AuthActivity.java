package ru.arturvasilov.stackexchangeclient;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Artur Vasilov
 */
public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onNewIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (TextUtils.equals(Intent.ACTION_VIEW, intent.getAction())) {
            Uri data = intent.getData();
            String accessToken = data.toString().split("#")[1].split("=")[1];
            PreferencesUtils.saveAccessToken(accessToken)
                    .subscribe(aBoolean -> {
                        MainActivity.start(AuthActivity.this);
                    });
        }
    }
}
