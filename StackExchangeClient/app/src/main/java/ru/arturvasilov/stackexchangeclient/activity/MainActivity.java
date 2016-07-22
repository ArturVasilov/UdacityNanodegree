package ru.arturvasilov.stackexchangeclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.arturvasilov.stackexchangeclient.R;
import ru.arturvasilov.stackexchangeclient.api.RepositoryProvider;
import ru.arturvasilov.stackexchangeclient.api.constants.ApiConstants;
import ru.arturvasilov.stackexchangeclient.rx.RxDecor;
import ru.arturvasilov.stackexchangeclient.rx.RxError;
import ru.arturvasilov.stackexchangeclient.rx.StubAction;
import ru.arturvasilov.stackexchangeclient.utils.Views;

/**
 * @author Artur Vasilov
 */
public class MainActivity extends AppCompatActivity {

    public static void start(@NonNull Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(Views.findById(this, R.id.toolbar));

        RepositoryProvider.getRepository()
                .getCurrentUser(ApiConstants.STACKOVERFLOW)
                .subscribe(new StubAction<>(), RxDecor.error(RxError.view(this, getSupportFragmentManager())));
    }
}
