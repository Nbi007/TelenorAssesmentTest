package com.telenor.herokuaapp.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.telenor.herokuaapp.R;

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {
    private static final String TAG = "TextView";

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
      /*  TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        String customFont = a.getString(R.styleable.CustomTextView_customFont);
        setCustomFont(ctx, customFont);
        a.recycle();*/
    }

    public boolean setCustomFont(Context ctx, String fontName) {
        Typeface typeface = null;
        try {
            if (fontName == null) {
                fontName = Constants.DEFAULT_FONT_NAME;
            }
            typeface = Typeface.createFromAsset(ctx.getAssets(), "fonts/" + fontName);
        } catch (Exception e) {
            Log.e(TAG, "Unable to load typeface: " + e.getMessage());
            return false;
        }
        setTypeface(typeface);
        return true;
    }

}
