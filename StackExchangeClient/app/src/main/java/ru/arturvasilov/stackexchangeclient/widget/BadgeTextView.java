package ru.arturvasilov.stackexchangeclient.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.TextView;

import ru.arturvasilov.stackexchangeclient.app.Env;
import ru.arturvasilov.stackexchangeclient.model.content.Badge;

/**
 * @author Artur Vasilov
 */
public class BadgeTextView extends TextView {

    public BadgeTextView(Context context) {
        super(context);
    }

    public BadgeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BadgeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBadge(@NonNull Badge badge) {
        setText(badge.getName());
        setOnClickListener(view -> {
            Env.browseUrl(getContext(), badge.getLink());
        });
    }

}
