package com.example.xyzreader.ui;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.xyzreader.R;

/**
 * @author Artur Vasilov
 */
public class CustomCardView extends CardView {

    public CustomCardView(Context context) {
        super(context);
    }

    public CustomCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                setCardElevation(getResources().getDimension(R.dimen.cardview_picked_up_elevation));
            } else if (event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP) {
                setCardElevation(getResources().getDimension(R.dimen.cardview_default_elevation));
            }
        }
        return super.onTouchEvent(event);
    }
}
