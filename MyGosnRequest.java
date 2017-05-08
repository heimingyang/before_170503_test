package com.example.before_170503_test;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by 黑明阳 on 2017/4/11.
 */

public class MyGosnRequest<T> extends Request<T> {

    private Gson gson;
    private Class aClass;
    private Response.Listener<T> mlistener;
    public MyGosnRequest(String url, Response.ErrorListener listener) {
        super(url, listener);
    }

    public MyGosnRequest(int method, String url, Class<T> aClass, Response.Listener<T> mlistener, Response.ErrorListener listener) {
        super(method, url, listener);
        gson=new Gson();
        this.aClass=aClass;
        this.mlistener=mlistener;

    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {

        try {
            String parsed= new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));

            return (Response<T>) Response.success(gson.fromJson(parsed,aClass),HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException var4) {
            return Response.error(new ParseError(var4));
        }
    }

    @Override
    protected void deliverResponse(T t) {
        this.mlistener.onResponse(t);
    }
}
