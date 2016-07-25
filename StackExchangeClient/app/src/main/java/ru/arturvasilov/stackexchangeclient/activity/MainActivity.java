package ru.arturvasilov.stackexchangeclient.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ru.arturvasilov.stackexchangeclient.R;
import ru.arturvasilov.stackexchangeclient.api.RepositoryProvider;
import ru.arturvasilov.stackexchangeclient.api.constants.ApiConstants;
import ru.arturvasilov.stackexchangeclient.model.content.User;
import ru.arturvasilov.stackexchangeclient.model.database.UserTable;
import ru.arturvasilov.stackexchangeclient.rx.RxDecor;
import ru.arturvasilov.stackexchangeclient.rx.RxError;
import ru.arturvasilov.stackexchangeclient.rx.StubAction;
import ru.arturvasilov.stackexchangeclient.utils.PreferencesUtils;
import ru.arturvasilov.stackexchangeclient.utils.Views;
import rx.functions.Action1;

/**
 * @author Artur Vasilov
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        setSupportActionBar(Views.findById(this, R.id.toolbar));
    }
}
