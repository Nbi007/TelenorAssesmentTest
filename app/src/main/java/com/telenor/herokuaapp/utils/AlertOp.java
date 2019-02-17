package com.telenor.herokuaapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.telenor.herokuaapp.R;

public class AlertOp {
    public static void showAlert(Context context, String title, String message, String btnTxtP, String btnTxtN, DialogInterface.OnClickListener onClickListener) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
            if (title != null)
                builder.setTitle(title);
            builder.setCancelable(false);
            builder.setMessage(message);
            builder.setPositiveButton(btnTxtP, onClickListener);
            builder.setNegativeButton(btnTxtN, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            final AlertDialog dialog = builder.create();
            dialog.show();
        } catch (
                Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * SNACK BAR WITH NO ACTION BUTTON AND DEFAULT BACKGROUND
     **/
    public static void showSnackbar(Context context, String message, boolean isLengthLong) {
        try {
            Snackbar snack = Snackbar.make(
                    (((Activity) context).findViewById(android.R.id.content)),
                    message + "", isLengthLong ? Snackbar.LENGTH_LONG : Snackbar.LENGTH_SHORT);

            View view = snack.getView();
            TextView tv = (TextView) view
                    .findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.WHITE);
            snack.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
