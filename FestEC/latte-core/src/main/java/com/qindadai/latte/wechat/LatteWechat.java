package com.qindadai.latte.wechat;

import android.app.Activity;

import com.qindadai.latte.app.ConfigKeys;
import com.qindadai.latte.app.Latte;
import com.qindadai.latte.wechat.callbacks.IWXSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by mymac on 2019-05-24.
 * func:
 */
public class LatteWechat {
    //appid appsecret
    static final String APP_ID = Latte.getConfiguration(ConfigKeys.WECHAT_ID);
    static final String APP_SECRET = Latte.getConfiguration(ConfigKeys.WECHAT_SECRET);

    private final IWXAPI WXAPI;
    private IWXSignInCallback wxSignInCallback;

    private static final class Holder {
        private static final LatteWechat INSTANCE = new LatteWechat();
    }

    public static LatteWechat getInstance() {
        return Holder.INSTANCE;
    }

    /***
     * 私有 构造
     */
    private LatteWechat() {
        final Activity activity = Latte.getConfiguration(ConfigKeys.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    public IWXAPI getWXAPI() {
        return WXAPI;
    }

    public IWXSignInCallback getWxSignInCallback(){
        return wxSignInCallback;
    }

    public LatteWechat onSignSuccess(IWXSignInCallback callback){
        this.wxSignInCallback = callback;
        return this;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
