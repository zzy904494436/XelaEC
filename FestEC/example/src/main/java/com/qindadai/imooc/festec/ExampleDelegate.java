package com.qindadai.imooc.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.qindadai.latte.delegates.LatteDelegate;
import com.qindadai.latte.net.RestClient;
import com.qindadai.latte.net.callback.IError;
import com.qindadai.latte.net.callback.IFailure;
import com.qindadai.latte.net.callback.ISuccess;

/**
 * Created by mymac on 2019/1/9.
 * func:
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    //建造者模式
    private void testRestClient() {
        RestClient.builder()
                .url("http://news.baidu.com/")
//                .params("", "")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void OnSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "onFailure", Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), "onError", Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();

    }

}
