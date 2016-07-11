package com.sam_chordas.android.stockhawk.utils;

import android.annotation.SuppressLint;
import android.content.ContentProviderOperation;
import android.support.annotation.NonNull;

import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author sam_chordas on 10/8/15.
 */
public class JsonParserUtils {

    public static boolean showPercent = true;

    @NonNull
    public static ArrayList<ContentProviderOperation> quoteJsonToContentValues(@NonNull String json) {
        ArrayList<ContentProviderOperation> batchOperations = new ArrayList<>();
        JSONObject jsonObject;
        JSONArray resultsArray;
        try {
            jsonObject = new JSONObject(json);
            if (jsonObject.length() != 0) {
                jsonObject = jsonObject.getJSONObject("query");
                int count = Integer.parseInt(jsonObject.getString("count"));
                if (count == 0) {
                    return batchOperations;
                }
                if (count == 1) {
                    jsonObject = jsonObject.getJSONObject("results")
                            .getJSONObject("quote");
                    buildBatchOperation(batchOperations, jsonObject);
                } else {
                    resultsArray = jsonObject.getJSONObject("results").getJSONArray("quote");
                    if (resultsArray != null && resultsArray.length() != 0) {
                        for (int i = 0; i < resultsArray.length(); i++) {
                            jsonObject = resultsArray.getJSONObject(i);
                            buildBatchOperation(batchOperations, jsonObject);
                        }
                    }
                }
            }
        } catch (JSONException ignored) {
        }
        return batchOperations;
    }

    @NonNull
    @SuppressLint("DefaultLocale")
    public static String truncateBidPrice(String bidPrice) {
        bidPrice = String.format("%.2f", Float.parseFloat(bidPrice));
        return bidPrice;
    }

    @SuppressLint("DefaultLocale")
    @NonNull
    public static String truncateChange(String change, boolean isPercentChange) {
        String weight = change.substring(0, 1);
        String ampersand = "";
        if (isPercentChange) {
            ampersand = change.substring(change.length() - 1, change.length());
            change = change.substring(0, change.length() - 1);
        }
        change = change.substring(1, change.length());
        double round = (double) Math.round(Double.parseDouble(change) * 100) / 100;
        change = String.format("%.2f", round);
        StringBuilder changeBuuilder = new StringBuilder(change);
        changeBuuilder.insert(0, weight);
        changeBuuilder.append(ampersand);
        change = changeBuuilder.toString();
        return change;
    }

    public static void buildBatchOperation(@NonNull ArrayList<ContentProviderOperation> operations,
                                                               @NonNull JSONObject jsonObject) {
        ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(
                QuoteProvider.Quotes.CONTENT_URI);
        try {
            String change = jsonObject.getString("Change");
            builder.withValue(QuoteColumns.SYMBOL, jsonObject.getString("symbol"));
            builder.withValue(QuoteColumns.BIDPRICE, truncateBidPrice(jsonObject.getString("Bid")));
            builder.withValue(QuoteColumns.PERCENT_CHANGE, truncateChange(
                    jsonObject.getString("ChangeinPercent"), true));
            builder.withValue(QuoteColumns.CHANGE, truncateChange(change, false));
            builder.withValue(QuoteColumns.ISCURRENT, 1);
            if (change.charAt(0) == '-') {
                builder.withValue(QuoteColumns.ISUP, 0);
            } else {
                builder.withValue(QuoteColumns.ISUP, 1);
            }
            operations.add(builder.build());
        } catch (Exception ignored) {
        }
    }
}
