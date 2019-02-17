package com.telenor.herokuaapp.utils;

import android.content.Context;

public class PixelsOp {
    public static int convertToDensity(Context context, int pixels) {
        float scale = context.getResources().getDisplayMetrics().density;
        return ((int) (pixels * scale + 0.5f));
    }

    public static float dpFromPx(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}
