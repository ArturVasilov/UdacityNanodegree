package com.sam_chordas.android.stockhawk.widget;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import com.google.android.gms.gcm.TaskParams;
import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.service.StockIntentService;
import com.sam_chordas.android.stockhawk.service.StockTaskService;
import com.sam_chordas.android.stockhawk.ui.MyStocksActivity;

/**
 * @author Artur Vasilov
 */
public class UpdateWidgetService extends IntentService {

    public UpdateWidgetService() {
        super(UpdateWidgetService.class.getName());
    }

    public UpdateWidgetService(String name) {
        super(name);
    }

    public static void start(@NonNull Context context, @NonNull int[] appWidgetIds) {
        Intent intent = new Intent(context, UpdateWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int[] appWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

        Bundle args = new Bundle();
        StockTaskService stockTaskService = new StockTaskService(this);
        stockTaskService.onRunTask(new TaskParams(StockIntentService.INIT, args));

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        for (int widgetId : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.widget_layout);

            Intent stocksIntent = new Intent(this, MyStocksActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, stocksIntent, 0);
            remoteViews.setOnClickPendingIntent(R.id.widget_title, pendingIntent);

            //Stocks activity will handle open details screen itself
            remoteViews.setPendingIntentTemplate(R.id.stocksListWidget, pendingIntent);
            Intent remoteViewIntent = new Intent(this, StocksRemoteViewsService.class);
            remoteViews.setRemoteAdapter(R.id.stocksListWidget, remoteViewIntent);
            remoteViews.setEmptyView(R.id.stocksListWidget, R.id.empty);

            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }
}
