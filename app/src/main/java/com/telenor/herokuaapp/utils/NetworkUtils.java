package com.telenor.herokuaapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.widget.Toast;

import com.telenor.herokuaapp.R;

public class NetworkUtils {
    /**
     * METHOD TO CHECK INTERNET CONNECTED
     */
    public static boolean isNetworkConnected(Context context, boolean showToast) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() == null) {
            if (showToast)
                showToast(context, context.getString(R.string.internet_connection_down));
            return false;
        }
        return true;
    }

    public static void showToast(Context context, String message) {
        try {
            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();
        } catch (Exception e) {

        }

    }
}
