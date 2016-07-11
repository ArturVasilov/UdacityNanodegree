package com.sam_chordas.android.stockhawk.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;

import com.google.android.gms.gcm.TaskParams;

/**
 * @author Artur Vasilov
 */
public class StockIntentService extends IntentService {

    static final String ACTION = "tag";
    static final String SYMBOL = "symbol";

    public static final String INIT = "init";
    public static final String ADD = "add";
    public static final String PERIODIC = "periodic";

    public StockIntentService() {
        super(StockIntentService.class.getName());
    }

    public StockIntentService(@NonNull String name) {
        super(name);
    }

    public static void start(@NonNull Context context, @NonNull @Action String action, @Nullable String symbol) {
        Intent intent = new Intent(context, StockIntentService.class);
        intent.putExtra(ACTION, action);
        if (symbol != null) {
            intent.putExtra(SYMBOL, symbol);
        }
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle args = new Bundle();
        if (ADD.equals(intent.getStringExtra(ACTION))) {
            args.putString(SYMBOL, intent.getStringExtra(SYMBOL));
        }
        // We can call OnRunTask from the intent service to force it to run immediately instead of
        // scheduling a task.
        StockTaskService stockTaskService = new StockTaskService(this);
        stockTaskService.onRunTask(new TaskParams(intent.getStringExtra(ACTION), args));
    }

    @StringDef({INIT, ADD})
    private @interface Action {}
}
