package com.udacity.gradle.androidlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * @author Artur Vasilov
 */
public class JokesActivity extends AppCompatActivity {

    private static final String JOKE_KEY = "joke_key";

    static void start(@NonNull Activity activity, @NonNull String joke) {
        Intent intent = new Intent(activity, JokesActivity.class);
        intent.putExtra(JOKE_KEY, joke);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        TextView jokeText = (TextView) findViewById(R.id.jokeTextView);
        String joke = getIntent().getStringExtra(JOKE_KEY);
        jokeText.setText(joke);
    }
}
