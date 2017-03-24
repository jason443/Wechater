package com.jason.wechater;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by jason on 2017/3/24.
 */

public class WechaterApplication extends Application{

    public static RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getmRequestQueue() {
        return mRequestQueue;
    }
}
