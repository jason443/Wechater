package com.jason.wechater;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.internal.http.multipart.Part;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by jason on 2017/3/18.
 */

public class TestActivity extends Activity{

    private static final String TAG = TestActivity.class.getSimpleName();
    private TextView mTvInfo;
    private ImageView mIvPhoto;
    private static final String SEND_URL = "http://www.21easyic.com/Android/test/login.asp";
    private static final String RECEIVE_URL = "http://www.21easyic.com/Android/test/getLogin.asp";
    private static final String RECEIVE_PHOTO_URL = "http://i0.hdslb.com/bfs/archive/07a856146fdb01c959e5a6f6cd49aed12d3332ee.jpg" ;
    private static final String SEND_PHOTO_URL = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mTvInfo = (TextView) findViewById(R.id.test_tv_info);
        mIvPhoto = (ImageView) findViewById(R.id.test_iv_prc);
    }

    private String setVolleyGetParams(HashMap<String,String> paramsList) {
        StringBuilder sb = new StringBuilder("?");
        for (String key:paramsList.keySet()) {
            sb.append(key).append("=").append(paramsList.get(key)).append("&");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public void onSend(View v) {
        HashMap<String,String> paramsList = new HashMap<>();
        paramsList.put("strName","wechater123");
        paramsList.put("strPassword","123456789");
        Log.d(TAG, "onSend: " + SEND_URL + setVolleyGetParams(paramsList));
        StringRequest stringRequest = new StringRequest(Request.Method.GET, SEND_URL+setVolleyGetParams(paramsList), new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                mTvInfo.setText(response);
                Log.d(TAG, "onResponse: ");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    mTvInfo.setText("上传失败");
                Log.d(TAG, "onErrorResponse: ");
            }
        });
        WechaterApplication.getmRequestQueue().add(stringRequest);
    }

    public void onReceive(View v) {
        StringRequest sr = new StringRequest(Request.Method.GET, RECEIVE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mTvInfo.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTvInfo.setText("获取数据失败");
            }
        });
        WechaterApplication.getmRequestQueue().add(sr);
    }

    public void onReceivePhoto(View v) {
        Glide.with(this).load(RECEIVE_PHOTO_URL).into(mIvPhoto);
    }

    public void onSendPhoto(View v) {
        StringRequest sr = new StringRequest(Request.Method.POST, SEND_PHOTO_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
    }

}
