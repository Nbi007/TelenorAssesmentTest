package com.telenor.herokuaapp.utils;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
@SuppressWarnings("deprecation")
public class ColorOp {
    public static final int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }
}
