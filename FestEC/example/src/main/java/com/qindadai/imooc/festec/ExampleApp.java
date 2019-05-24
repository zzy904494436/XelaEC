package com.qindadai.imooc.festec;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.qindadai.latte.app.Latte;
import com.qindadai.latte.ec.database.DatabaseManager;
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
                .withWechatAppId("")
                .withWechatAppSecret("")
                .configure();
        //初始化 Stetho
//        initStetho();
        //初始化 data
        DatabaseManager.getInstance().init(this);
    }

    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}
