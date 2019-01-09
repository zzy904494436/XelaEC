package com.qindadai.imooc.festec;

import android.app.Application;

import com.qindadai.latte.app.Latte;

/**
 * Created by mymac on 2019/1/9.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://127.0.0.1")
                .congigure();
    }

}
