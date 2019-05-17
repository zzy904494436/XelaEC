package com.qindadai.latte.util.timer;


import java.util.TimerTask;

/**
 * Created by mymac on 2019/1/10.
 * func:
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener = null;
    public  BaseTimerTask(ITimerListener timerListener){
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener!= null){

        }
    }
}
