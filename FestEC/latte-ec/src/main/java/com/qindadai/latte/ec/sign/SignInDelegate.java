package com.qindadai.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.joanzapata.iconify.widget.IconTextView;
import com.qindadai.latte.delegates.LatteDelegate;
import com.qindadai.latte.ec.R;
import com.qindadai.latte.ec.R2;
import com.qindadai.latte.net.RestClient;
import com.qindadai.latte.net.callback.ISuccess;
import com.qindadai.latte.util.log.LatteLogger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by mymac on 2019-05-24.
 * func:
 */
public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText emailView;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText pswdView;

    private ISignListener mISignListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }


    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if (checkForm()) {
            RestClient.builder()
                    .url("sign_in")
                    .params("", "")
                    .success(new ISuccess() {
                        @Override
                        public void OnSuccess(String response) {
                            LatteLogger.json("USER_PROFILE" ,response);
                            SignHandler.onSignIn(response, mISignListener);
                        }
                    })
                    .build()
                    .post();
            Toast.makeText(getContext(), "验证通过", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R2.id.tv_sign_in_link)
    void onClickLink() {
        start(new SignUpDelegate());
    }

    @OnClick(R2.id.itv_sign_in_wechat)
    void onClickWechat() {
    }

    private boolean checkForm() {
        final String email = emailView.getText().toString();
        final String password = pswdView.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailView.setError("错误的邮箱格式");
            isPass = false;
        } else {
            emailView.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            pswdView.setError("请至少填写6位密码");
            isPass = false;
        } else {
            pswdView.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
