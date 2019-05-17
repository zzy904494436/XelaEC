package com.qindadai.latte.net.rx;

import android.content.Context;

import com.qindadai.latte.net.RestClient;
import com.qindadai.latte.net.RestCreator;
import com.qindadai.latte.net.callback.IError;
import com.qindadai.latte.net.callback.IFailure;
import com.qindadai.latte.net.callback.IRequest;
import com.qindadai.latte.net.callback.ISuccess;
import com.qindadai.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by mymac on 2019/5/16.
 * func:
 */
public class RxRestClientBuilder {

    private String mUrl;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private RequestBody mBody;
    private File mFile;
    private Context context;
    private LoaderStyle loaderStyle;

    RxRestClientBuilder() {
    }

    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RxRestClientBuilder loader(Context context) {
        this.context = context;
        this.loaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RxRestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        this.context = context;
        this.loaderStyle = loaderStyle;
        return this;
    }

    public final RxRestClient build() {
        return new RxRestClient(mUrl, PARAMS , mBody, mFile, context, loaderStyle);
    }
}
