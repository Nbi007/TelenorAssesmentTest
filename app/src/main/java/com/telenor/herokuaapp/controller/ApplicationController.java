package com.telenor.herokuaapp.controller;

import android.app.Application;
import android.content.Context;
/**
 * Created by nofal bin idrees on 2/17/2019.
 */
public class ApplicationController extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public static Context getContext() {
        return context;
    }
}
