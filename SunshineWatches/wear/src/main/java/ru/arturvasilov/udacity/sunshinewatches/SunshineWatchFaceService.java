package ru.arturvasilov.udacity.sunshinewatches;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.wearable.watchface.CanvasWatchFaceService;
import android.support.wearable.watchface.WatchFaceStyle;
import android.view.SurfaceHolder;
import android.view.WindowInsets;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Artur Vasilov
 */
public class SunshineWatchFaceService extends CanvasWatchFaceService {

    private static final long UPDATE_RATE = TimeUnit.MINUTES.toMillis(1);

    /**
     * Handler message id for updating the time periodically in interactive mode.
     */
    private static final int MSG_UPDATE_TIME = 0;

    @Override
    public SunshineEngine onCreateEngine() {
        return new SunshineEngine();
    }

    private class SunshineEngine extends CanvasWatchFaceService.Engine implements DataApi.DataListener,
            GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

        private static final String WEATHER_PATH = "/weather";
        private static final String WEATHER_INFO_PATH = "/weather-info";

        private static final String KEY_UUID = "uuid";
        private static final String KEY_HIGH = "high";
        private static final String KEY_LOW = "low";
        private static final String KEY_WEATHER_ID = "weatherId";

        private final Context mContext;

        private final Handler mUpdateTimeHandler;

        private final GoogleApiClient mGoogleApiClient;

        private final Paint mBackgroundPaint;
        private final Paint mTextHoursPaint;
        private final Paint mTextMinutePaint;
        private final Paint mTextDatePaint;
        private final Paint mDividerPaint;
        private final Paint mTemperatureHighPaint;
        private final Paint mTemperatureLowPaint;
        private final Paint mIconPaint;

        private final float mTimeYOffset;
        private final float mDateYOffset;
        private final float mDividerYOffset;
        private final float mDividerWidth;
        private final float mForecastYOffset;
        private final float mSmallPadding;

        private Bitmap mWeatherIcon;
        private String mWeatherHigh;
        private String mWeatherLow;

        public SunshineEngine() {
            mContext = SunshineWatchFaceService.this;

            mUpdateTimeHandler = new EngineHandler(this);
            mGoogleApiClient = buildClient();

            mBackgroundPaint = new Paint();
            mBackgroundPaint.setColor(ContextCompat.getColor(mContext, R.color.primary_dark));

            Typeface lightBold = Typeface.create("sans-serif-light", Typeface.BOLD);
            Typeface lightNormal = Typeface.create("sans-serif-light", Typeface.NORMAL);
            Typeface normal = Typeface.create("sans-serif", Typeface.NORMAL);

            mTextHoursPaint = buildPaint(R.color.white, lightBold);
            mTextMinutePaint = buildPaint(R.color.white, lightNormal);
            mTextDatePaint = buildPaint(R.color.white_50, lightNormal);

            mDividerPaint = new Paint();
            mDividerPaint.setColor(ContextCompat.getColor(mContext, R.color.primary_light));
            mDividerPaint.setStrokeWidth(1f);

            mTemperatureHighPaint = buildPaint(R.color.white, normal);
            mTemperatureLowPaint = buildPaint(R.color.white_50, normal);

            mIconPaint = new Paint();
            mIconPaint.setAntiAlias(true);

            mTimeYOffset = mContext.getResources().getDimension(R.dimen.time_margin_top);
            mDateYOffset = mContext.getResources().getDimension(R.dimen.date_margin_top);
            mDividerYOffset = mContext.getResources().getDimension(R.dimen.divider_margin_top);
            mDividerWidth = mContext.getResources().getDimension(R.dimen.divider_width);
            mForecastYOffset = mContext.getResources().getDimension(R.dimen.forecast_margin_top);
            mSmallPadding = mContext.getResources().getDimension(R.dimen.margin_small);
        }

        @Override
        public void onCreate(SurfaceHolder holder) {
            super.onCreate(holder);

            setWatchFaceStyle(new WatchFaceStyle.Builder(SunshineWatchFaceService.this)
                    .setCardPeekMode(WatchFaceStyle.PEEK_MODE_SHORT)
                    .setBackgroundVisibility(WatchFaceStyle.BACKGROUND_VISIBILITY_INTERRUPTIVE)
                    .setShowSystemUiTime(false)
                    .build());
        }

        @Override
        public void onDestroy() {
            mUpdateTimeHandler.removeMessages(MSG_UPDATE_TIME);
            super.onDestroy();
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);

            if (visible) {
                mGoogleApiClient.connect();
            } else if (mGoogleApiClient.isConnected()) {
                Wearable.DataApi.removeListener(mGoogleApiClient, this);
                mGoogleApiClient.disconnect();
            }

            updateTimer();
        }

        @Override
        public void onApplyWindowInsets(WindowInsets insets) {
            super.onApplyWindowInsets(insets);

            float timeTextSize = mContext.getResources().getDimension(R.dimen.text_32);
            float dateTextSize = mContext.getResources().getDimension(R.dimen.text_16);
            float temperatureTextSize = mContext.getResources().getDimension(R.dimen.text_22);

            mTextHoursPaint.setTextSize(timeTextSize);
            mTextMinutePaint.setTextSize(timeTextSize);
            mTextDatePaint.setTextSize(dateTextSize);
            mTemperatureHighPaint.setTextSize(temperatureTextSize);
            mTemperatureLowPaint.setTextSize(temperatureTextSize);
        }

        @Override
        public void onTimeTick() {
            super.onTimeTick();
            invalidate();
        }

        @Override
        public void onDraw(Canvas canvas, Rect bounds) {
            canvas.drawRect(0, 0, bounds.width(), bounds.height(), mBackgroundPaint);
            Calendar calendar = Calendar.getInstance();

            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            String hoursText = (hour < 10) ? ("0" + hour + ":") : (hour + ":");
            String minutesText = (minute < 10) ? ("0" + minute) : String.valueOf(minute);

            float colonSize = mTextHoursPaint.measureText(":");
            float hoursTextLen = mTextHoursPaint.measureText(hoursText);
            float offsetX = bounds.centerX() - hoursTextLen + (colonSize / 2);

            canvas.drawText(hoursText, offsetX, mTimeYOffset, mTextHoursPaint);
            canvas.drawText(minutesText, bounds.centerX() + (colonSize / 2), mTimeYOffset, mTextMinutePaint);

            String dateText = "FRI, JUL 14 2015";
            float dateTextLength = mTextDatePaint.measureText(dateText);
            canvas.drawText(dateText, bounds.centerX() - dateTextLength / 2, mDateYOffset, mTextDatePaint);

            canvas.drawLine(bounds.centerX() - (mDividerWidth / 2), mDividerYOffset,
                    bounds.centerX() + (mDividerWidth / 2), mDividerYOffset, mDividerPaint);

            //TODO : remove
            if (mWeatherHigh == null) {
                mWeatherHigh = "26";
            }
            if (mWeatherLow == null) {
                mWeatherLow = "15";
            }
            if (mWeatherIcon == null) {
                mWeatherIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_clear);
            }

            String highTemp = mWeatherHigh + "\u00B0 ";
            String lowTemp = mWeatherLow + "\u00B0";
            float highTempLen = mTemperatureHighPaint.measureText(highTemp);

            canvas.drawText(highTemp, bounds.centerX() - mSmallPadding, mForecastYOffset, mTemperatureHighPaint);
            canvas.drawText(lowTemp, bounds.centerX() - mSmallPadding + highTempLen, mForecastYOffset, mTemperatureLowPaint);

            int imageHeight = mWeatherIcon.getHeight();
            int imageWidth = mWeatherIcon.getWidth();
            canvas.drawBitmap(mWeatherIcon, bounds.centerX() - 3 * mSmallPadding - imageWidth,
                    mForecastYOffset - (float) (imageHeight / 1.5), mIconPaint);
        }

        @Override
        public void onConnected(Bundle bundle) {
            Wearable.DataApi.addListener(mGoogleApiClient, SunshineEngine.this);
            requestWeatherInfo();
        }

        @Override
        public void onConnectionSuspended(int i) {
            // Do nothing
        }

        @Override
        public void onDataChanged(DataEventBuffer dataEvents) {
            for (DataEvent dataEvent : dataEvents) {
                if (dataEvent.getType() == DataEvent.TYPE_CHANGED) {
                    DataMap dataMap = DataMapItem.fromDataItem(dataEvent.getDataItem()).getDataMap();
                    String path = dataEvent.getDataItem().getUri().getPath();
                    if (path.equals(WEATHER_INFO_PATH)) {
                        mWeatherHigh = dataMap.getString(KEY_HIGH);
                        mWeatherLow = dataMap.getString(KEY_LOW);

                        if (dataMap.containsKey(KEY_WEATHER_ID)) {
                            int weatherId = dataMap.getInt(KEY_WEATHER_ID);
                            Drawable b = ContextCompat.getDrawable(mContext, Utility.getIconResourceForWeatherCondition(weatherId));
                            Bitmap icon = ((BitmapDrawable) b).getBitmap();
                            float scaledWidth = (mTemperatureHighPaint.getTextSize() / icon.getHeight()) * icon.getWidth();
                            mWeatherIcon = Bitmap.createScaledBitmap(icon, (int) scaledWidth, (int) mTemperatureHighPaint.getTextSize(), true);
                        }

                        invalidate();
                    }
                }
            }
        }

        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            // Do nothing
        }

        @NonNull
        private Paint buildPaint(@ColorRes int textColor, @NonNull Typeface typeface) {
            Paint paint = new Paint();
            paint.setColor(ContextCompat.getColor(mContext, textColor));
            paint.setTypeface(typeface);
            paint.setAntiAlias(true);
            return paint;
        }

        @NonNull
        private GoogleApiClient buildClient() {
            return new GoogleApiClient.Builder(SunshineWatchFaceService.this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(Wearable.API)
                    .build();
        }

        private void updateTimer() {
            mUpdateTimeHandler.removeMessages(MSG_UPDATE_TIME);
            if (isVisible()) {
                mUpdateTimeHandler.sendEmptyMessage(MSG_UPDATE_TIME);
            }
        }

        private void handleUpdateTimeMessage() {
            invalidate();
            if (isVisible()) {
                long timeMs = System.currentTimeMillis();
                long delayMs = UPDATE_RATE - (timeMs % UPDATE_RATE);
                mUpdateTimeHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIME, delayMs);
            }
        }

        private void requestWeatherInfo() {
            PutDataMapRequest putDataMapRequest = PutDataMapRequest.create(WEATHER_PATH);
            putDataMapRequest.getDataMap().putString(KEY_UUID, UUID.randomUUID().toString());
            PutDataRequest request = putDataMapRequest.asPutDataRequest();

            Wearable.DataApi.putDataItem(mGoogleApiClient, request);
        }
    }

    private static class EngineHandler extends Handler {

        private final WeakReference<SunshineEngine> mWeakReference;

        public EngineHandler(@NonNull SunshineEngine reference) {
            mWeakReference = new WeakReference<>(reference);
        }

        @Override
        public void handleMessage(Message msg) {
            SunshineEngine engine = mWeakReference.get();
            if (engine != null) {
                switch (msg.what) {
                    case MSG_UPDATE_TIME:
                        engine.handleUpdateTimeMessage();
                        break;
                }
            }
        }
    }
}
