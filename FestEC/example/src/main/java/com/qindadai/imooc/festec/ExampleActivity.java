package com.qindadai.imooc.festec;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import com.qindadai.latte.activities.ProxyActivity;
import com.qindadai.latte.delegates.LatteDelegate;
import com.qindadai.latte.ec.sign.SignInDelegate;
import com.qindadai.latte.ec.sign.SignUpDelegate;

/***

 latte-EC
 app    ---    功能module 1 latte-UI  ----   core (latte-core)
 ---    功能module 2 latte-XXX  ----

 注解 module  latte-annotations(java)

 ---    代码生成器annotationProcessor apt  latte-compiler(java) （ 通过注解生成动态代码）

 latte-拿铁

 第二课
 1: 注解module  latte-annotations java

 2: compiler module      java
 代码生成器annotationProcessor : butterknife  dagger

 3: 核心model
 路由 - 数据周转

 http请求框架 ：okhttp 、 retrofit
 照片、二维码 图片剪裁
 通用Ui ：banner recyclerView
 通用工具类
 WebView处理：
 微信登录-支付、支付宝支付
 重复性处理 下载缓存 sharedpreferences
 4：业务 model
 项目特点 针对情况特殊处理
 application 签名 验证

 名字-latte

 第三课 ：
 建立项目
 *
 */
public class ExampleActivity extends ProxyActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new SignInDelegate();
    }
}
