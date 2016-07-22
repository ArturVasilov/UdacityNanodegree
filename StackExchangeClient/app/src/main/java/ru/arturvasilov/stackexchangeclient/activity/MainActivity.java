package ru.arturvasilov.stackexchangeclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Artur Vasilov
 */
public class MainActivity extends AppCompatActivity {

    public static void start(@NonNull Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

}
