package com.jason.wechater.net;

import android.provider.Telephony;

import com.android.internal.http.multipart.Part;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by jason on 2017/3/25.
 */

public class MultipartRequest extends StringRequest{

    private Part[] parts;

    public MultipartRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener,Part[] parts) {
        super(Method.POST,url, listener, errorListener);
        this.parts = parts;
    }

    @Override
    public String getBodyContentType() {
        return "multipart/from-data;boundary" + Part.getBoundary();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            Part.sendParts(baos,parts);
        } catch (IOException e) {
            VolleyLog.e(e, "error when sending parts to output!");
        }
        return baos.toByteArray();
    }
}
