package com.qindadai.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.qindadai.latte.delegates.LatteDelegate;
import com.qindadai.latte.ec.R;
import com.qindadai.latte.util.timer.ITimerListener;

import java.util.Timer;

/**
 * Created by mymac on 2019/1/10.
 * func:
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener {

//    @BindView(R.id.)  //todo
    AppCompatTextView mTvTimer;

    private Timer mTimer = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onTimer() {

    }
}
