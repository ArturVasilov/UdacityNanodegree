package ru.arturvasilov.udacity.sunshinewatches;

import android.support.annotation.NonNull;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;

/**
 * @author Artur Vasilov
 */

public class WearableSync {

    private static final String REQUEST_KEY = "/sunshine";

    private static final String HIGH_KEY = "high";
    private static final String LOW_KEY = "low";
    private static final String CONDITION_KEY = "condition";

    private DataMap mDataMap;

    public boolean shouldHandleEvent(@NonNull DataEvent dataEvent) {
        if (dataEvent.getType() == DataEvent.TYPE_CHANGED) {
            DataMap dataMap = DataMapItem.fromDataItem(dataEvent.getDataItem()).getDataMap();
            String path = dataEvent.getDataItem().getUri().getPath();
            if (path.equals(REQUEST_KEY)) {
                mDataMap = dataMap;
                return true;
            }
        }
        return false;
    }

    public int getHighTemperature() {
        return mDataMap.getInt(HIGH_KEY);
    }

    public int getLowTemperature() {
        return mDataMap.getInt(LOW_KEY);
    }

    public int getCondition() {
        return mDataMap.getInt(CONDITION_KEY);
    }
}
