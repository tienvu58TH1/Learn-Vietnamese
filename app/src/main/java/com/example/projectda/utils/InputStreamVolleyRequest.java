package com.example.projectda.utils;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.Map;

public class InputStreamVolleyRequest extends Request<byte[]> {
    private final Response.Listener<byte[]> listener;

    public InputStreamVolleyRequest(int method, String url, Response.Listener<byte[]> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        setShouldCache(false);
        this.listener = listener;
    }
    @Override
    protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {


        //Pass the response data here
        return Response.success(response.data, HttpHeaderParser.parseCacheHeaders(response));
    }
    @Override
    protected void deliverResponse(byte[] response) {
        listener.onResponse(response);
    }


}