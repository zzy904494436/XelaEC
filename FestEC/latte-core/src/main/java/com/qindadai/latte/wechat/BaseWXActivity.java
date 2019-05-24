package com.qindadai.latte.wechat;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.qindadai.latte.app.Latte;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * Created by mymac on 2019-05-24.
 * func:
 */
public abstract class BaseWXActivity extends AppCompatActivity implements IWXAPIEventHandler {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ONCREATE
        LatteWechat.getInstance().getWXAPI().handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        LatteWechat.getInstance().getWXAPI().handleIntent(getIntent(), this);
    }
}
