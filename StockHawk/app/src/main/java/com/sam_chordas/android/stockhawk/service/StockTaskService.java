package com.sam_chordas.android.stockhawk.service;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;
import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;
import com.sam_chordas.android.stockhawk.utils.JsonParserUtils;
import com.sam_chordas.android.stockhawk.utils.Network;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sam_chordas on 9/30/15.
 * The GCMTask service is primarily for periodic tasks. However, OnRunTask can be called directly
 * and is used for the initialization and adding task as well.
 */
public class StockTaskService extends GcmTaskService {

    private final OkHttpClient mClient = new OkHttpClient();
    private final StringBuilder mStoredSymbols = new StringBuilder();
    private boolean mIsUpdate;
    private Context mContext;

    @SuppressWarnings("unused")
    public StockTaskService() {
    }

    public StockTaskService(@NonNull Context context) {
        mContext = context;
    }

    @Override
    public int onRunTask(TaskParams params) {
        if (mContext == null) {
            mContext = this;
        }

        StringBuilder urlStringBuilder = new StringBuilder();
        try {
            // Base URL for the Yahoo query
            urlStringBuilder.append("https://query.yahooapis.com/v1/public/yql?q=");
            urlStringBuilder.append(URLEncoder.encode("select * from yahoo.finance.quotes where symbol "
                    + "in (", "UTF-8"));
        } catch (UnsupportedEncodingException ignored) {
        }

        if (StockIntentService.INIT.equals(params.getTag()) || StockIntentService.PERIODIC.equals(params.getTag())) {
            mIsUpdate = true;
            Cursor initQueryCursor = mContext.getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,
                    new String[]{"Distinct " + QuoteColumns.SYMBOL}, null,
                    null, null);
            if (initQueryCursor == null || initQueryCursor.getCount() == 0) {
                try {
                    urlStringBuilder.append(URLEncoder.encode("\"YHOO\",\"AAPL\",\"GOOG\",\"MSFT\")", "UTF-8"));
                } catch (UnsupportedEncodingException ignored) {
                }
            } else {
                DatabaseUtils.dumpCursor(initQueryCursor);
                initQueryCursor.moveToFirst();
                for (int i = 0; i < initQueryCursor.getCount(); i++) {
                    mStoredSymbols.append("\"")
                            .append(initQueryCursor.getString(initQueryCursor.getColumnIndex("symbol")))
                            .append("\",");
                    initQueryCursor.moveToNext();
                }
                mStoredSymbols.replace(mStoredSymbols.length() - 1, mStoredSymbols.length(), ")");
                try {
                    urlStringBuilder.append(URLEncoder.encode(mStoredSymbols.toString(), "UTF-8"));
                } catch (UnsupportedEncodingException ignored) {
                }
            }
        } else if (StockIntentService.ADD.equals(params.getTag())) {
            mIsUpdate = false;
            // get symbol from params.getExtra and build query
            String stockInput = params.getExtras().getString("symbol");
            try {
                urlStringBuilder.append(URLEncoder.encode("\"" + stockInput + "\")", "UTF-8"));
            } catch (UnsupportedEncodingException ignored) {
            }
        }
        // finalize the URL for the API query.
        urlStringBuilder.append("&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.")
                .append("org%2Falltableswithkeys&callback=");

        String urlString;
        String response;
        int result = GcmNetworkManager.RESULT_FAILURE;

        urlString = urlStringBuilder.toString();
        try {
            response = fetchData(urlString);
            result = GcmNetworkManager.RESULT_SUCCESS;
            try {
                ContentValues contentValues = new ContentValues();
                // update ISCURRENT to 0 (false) so new data is current
                if (mIsUpdate) {
                    contentValues.put(QuoteColumns.ISCURRENT, 0);
                    mContext.getContentResolver().update(QuoteProvider.Quotes.CONTENT_URI, contentValues, null, null);
                }
                ArrayList<ContentProviderOperation> operations = JsonParserUtils.quoteJsonToContentValues(response);
                if (!operations.isEmpty()) {
                    mContext.getContentResolver().applyBatch(QuoteProvider.AUTHORITY, operations);
                } else {
                    handleEmptyResponse();
                }
            } catch (RemoteException | OperationApplicationException ignored) {
            }
        } catch (IOException e) {
            handleError();
        }

        return result;
    }

    @NonNull
    private String fetchData(@NonNull String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = mClient.newCall(request).execute();
        return response.body().string();
    }

    private void handleError() {
        if (Network.hasNetwork(mContext)) {
            showMessage(R.string.system_error);
        } else {
            showMessage(R.string.no_internet_error);
        }
    }

    private void handleEmptyResponse() {
        showMessage(R.string.input_error);
    }

    private void showMessage(@StringRes int stringId) {
        final String text = mContext.getString(stringId);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(mContext, text, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, Gravity.CENTER, 0);
                toast.show();
            }
        });
    }

}
