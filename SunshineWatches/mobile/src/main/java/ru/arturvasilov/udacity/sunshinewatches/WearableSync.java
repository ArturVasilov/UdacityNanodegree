package ru.arturvasilov.udacity.sunshinewatches;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.Wearable;

/**
 * @author Artur Vasilov
 */
public class WearableSync {

    private static final String REQUEST_KEY = "/sunshine";

    private static final String HIGH_KEY = "high";
    private static final String LOW_KEY = "low";
    private static final String CONDITION_KEY = "condition";
    private static final String TIME = "time";

    private final GoogleApiClient mGoogleApiClient;

    public WearableSync(@NonNull Context context) {
        mGoogleApiClient = new GoogleApiClient.Builder(context).addApi(Wearable.API).build();
        mGoogleApiClient.connect();
    }

    public void sendMessageToWear(int high, int low, int weatherCondition) {
        PutDataMapRequest requestMap = PutDataMapRequest.create(REQUEST_KEY);
        requestMap.getDataMap().putInt(HIGH_KEY, high);
        requestMap.getDataMap().putInt(LOW_KEY, low);
        requestMap.getDataMap().putInt(CONDITION_KEY, weatherCondition);
        requestMap.getDataMap().putLong(TIME, System.currentTimeMillis());

        Wearable.DataApi.putDataItem(mGoogleApiClient, requestMap.asPutDataRequest());
    }
}