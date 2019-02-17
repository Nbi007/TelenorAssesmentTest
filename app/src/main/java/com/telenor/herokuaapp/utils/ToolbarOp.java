package com.telenor.herokuaapp.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.telenor.herokuaapp.R;

public class ToolbarOp {
    public static enum Theme {
        Dark, Light;
    }
    public static void addIcon(Toolbar tb, Context context, int drawableResource, int gravity, int leftMargin, int rightMargin, View.OnClickListener onClickListener) {

        Toolbar.LayoutParams params = new Toolbar.LayoutParams((int) PixelsOp.pxFromDp(context, 45.0f), Toolbar.LayoutParams.MATCH_PARENT);

        if (gravity == Gravity.NO_GRAVITY)
            params.leftMargin = leftMargin;
        params.gravity = gravity;

        RelativeLayout relativeLayout = new RelativeLayout(context);

        RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        if (gravity == Gravity.END)
            imageParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);

        if (gravity == Gravity.NO_GRAVITY) {
            imageParams.leftMargin = (int) PixelsOp.pxFromDp(context, 12.0f);
        }

        if (gravity != Gravity.NO_GRAVITY) {
            imageParams.leftMargin = leftMargin;
            imageParams.rightMargin = rightMargin;
        }

        ImageView imageView = new ImageView(context);

        imageView.setImageResource(drawableResource);
        tb.addView(relativeLayout, params);
        relativeLayout.addView(imageView, imageParams);
        relativeLayout.setOnClickListener(onClickListener);
    }

    public static void addTitle(Toolbar tb, Context context, String title, Theme theme, boolean isCenter) {
        CustomTextView tvTitle = new CustomTextView(context);
        tvTitle.setSingleLine(true);
        tvTitle.setEllipsize(TextUtils.TruncateAt.END);

        tb.addView(tvTitle);

        if (isCenter) {
            Toolbar.LayoutParams params = (Toolbar.LayoutParams) tvTitle.getLayoutParams();
            params.gravity = Gravity.CENTER;
            tvTitle.setLayoutParams(params);
        }

        tvTitle.setCustomFont(context, Constants.DEFAULT_FONT_NAME);
        tb.setVisibility(View.VISIBLE);

        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                20);
        tvTitle.setSingleLine(true);
        tvTitle.setText(title);

        if (theme == Theme.Dark) {
            tvTitle.setTextColor(ColorOp.getColor(context,
                    R.color.white));

            tvTitle.setCustomFont(context, "Roboto-Regular.ttf");
            tb.setBackgroundResource(R.color.colorPrimary);
        } else {
            tb.setBackgroundResource(R.color.white);
            tvTitle.setTextColor(ColorOp.getColor(context,
                    R.color.white));
        }
    }
}
