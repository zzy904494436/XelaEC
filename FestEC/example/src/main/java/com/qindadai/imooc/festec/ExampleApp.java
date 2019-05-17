package com.qindadai.imooc.festec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.qindadai.latte.app.Latte;
import com.qindadai.latte.ec.icon.FontEcModule;
import com.qindadai.latte.net.interceptors.DebugInterceptor;

/**
 * Created by mymac on 2019/1/9.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
    }

}
