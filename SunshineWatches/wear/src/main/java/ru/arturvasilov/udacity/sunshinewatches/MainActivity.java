package ru.arturvasilov.udacity.sunshinewatches;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.wearable.activity.WearableActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

public class MainActivity extends WearableActivity {

    private TextView mTimeTextView;
    private TextView mDateTextView;
    private TextView mForecastTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        mTimeTextView = (TextView) findViewById(R.id.timeTextView);
        mDateTextView = (TextView) findViewById(R.id.dateTextView);
        mForecastTextView = (TextView) findViewById(R.id.forecastTextView);
        updateDisplay();
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {
        SpannableString time = new SpannableString("15:50");
        time.setSpan(new StyleSpan(Typeface.BOLD), 0, 3, 0);
        mTimeTextView.setText(time);

        String date = "FRI, JUL 14 2015";
        mDateTextView.setText(date);

        int max = 25;
        int min = 16;
        String temperature = getString(R.string.temperature_format, max, min);
        SpannableString temperatureSpan = new SpannableString(temperature);
        temperatureSpan.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.white_50)),
                temperature.indexOf(" ") + 1, temperature.length(), 0);
        mForecastTextView.setText(temperatureSpan);
    }
}
