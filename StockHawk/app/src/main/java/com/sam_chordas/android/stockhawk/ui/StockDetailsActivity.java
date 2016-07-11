package com.sam_chordas.android.stockhawk.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Artur Vasilov
 */
public class StockDetailsActivity extends AppCompatActivity {

    private static final String STOCK_ID = "stock_id";

    public static void start(@NonNull Activity activity, @NonNull String stockId) {
        Intent intent = new Intent(activity, StockDetailsActivity.class);
        intent.putExtra(STOCK_ID, stockId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
