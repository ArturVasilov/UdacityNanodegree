package ru.arturvasilov.stackexchangeclient.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.Button;

import ru.arturvasilov.stackexchangeclient.app.Env;
import ru.arturvasilov.stackexchangeclient.model.content.Badge;

/**
 * @author Artur Vasilov
 */
public class BadgeButton extends Button {

    public BadgeButton(Context context) {
        super(context);
    }

    public BadgeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BadgeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBadge(@NonNull Badge badge) {
        setText(badge.getName());
        setOnClickListener(view -> Env.browseUrl(getContext(), badge.getLink()));
    }

}
