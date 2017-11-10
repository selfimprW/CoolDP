package com.yaerin.cooldp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.StringRes;

/**
 * Created by yaerin on 9/2/17.
 */

@SuppressLint("ShowToast")
public class Toast {

    public static final int LENGTH_LONG = android.widget.Toast.LENGTH_LONG;
    public static final int LENGTH_SHORT = android.widget.Toast.LENGTH_SHORT;

    private static Context sContext;
    private static android.widget.Toast sToast;

    public static void initialize(Context context) {
        sContext = context;
    }

    public static void makeText(final CharSequence text, final int duration) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (sToast == null) {
                    sToast = android.widget.Toast.makeText(sContext, text, duration);
                } else {
                    sToast.setText(text);
                    sToast.setDuration(duration);
                }
                sToast.show();
            }
        });
    }

    public static void makeText(@StringRes final int resId, final int duration) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (sToast == null) {
                    sToast = android.widget.Toast.makeText(sContext, resId, duration);
                } else {
                    sToast.setText(resId);
                    sToast.setDuration(duration);
                }
                sToast.show();
            }
        });
    }

}