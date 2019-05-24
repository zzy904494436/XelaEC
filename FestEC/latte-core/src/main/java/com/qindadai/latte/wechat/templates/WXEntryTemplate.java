package com.qindadai.latte.wechat.templates;

import com.qindadai.latte.wechat.BaseWXEntryActivity;
import com.qindadai.latte.wechat.LatteWechat;

/**
 * Created by mymac on 2019-05-24.
 * func:
 */
public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWechat.getInstance().getWxSignInCallback().onSignInCallback(userInfo);
    }
}
