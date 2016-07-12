package com.udacity.gradle.builditbigger;

import android.support.annotation.NonNull;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * @author Artur Vasilov
 */
public final class AdsUtils {

    private AdsUtils() {
    }

    public static void showAds(@NonNull View view) {
        AdView adView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
    }

}
