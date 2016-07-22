package ru.arturvasilov.stackexchangeclient.api.constants;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Artur Vasilov
 */
@Retention(RetentionPolicy.SOURCE)
@StringDef({ApiConstants.STACKOVERFLOW})
public @interface Site {
}
