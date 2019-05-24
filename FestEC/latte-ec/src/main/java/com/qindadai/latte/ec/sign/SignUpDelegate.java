package com.qindadai.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.qindadai.latte.delegates.LatteDelegate;
import com.qindadai.latte.ec.R;
import com.qindadai.latte.ec.R2;
import com.qindadai.latte.net.RestClient;
import com.qindadai.latte.net.callback.ISuccess;
import com.qindadai.latte.util.log.LatteLogger;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by mymac on 2019-05-22.
 * func:
 */
public class SignUpDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText nameView;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText emailView;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText phoneView;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText pswdView;
    @BindView(R2.id.edit_sign_up_repassword)
    TextInputEditText repswdView;

    private ISignListener mISignListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
            RestClient.builder()
                    .url("sign_up")
                    .params("", "")
                    .success(new ISuccess() {
                        @Override
                        public void OnSuccess(String response) {
                            LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignUp(response , mISignListener );
                        }
                    })
                    .build()
                    .post();
            Toast.makeText(getContext(), "验证通过", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R2.id.tv_sign_up_link)
    void onClickLink() {
        start(new SignInDelegate());
    }

    private boolean checkForm() {
        final String name = nameView.getText().toString();
        final String email = emailView.getText().toString();
        final String phone = phoneView.getText().toString();
        final String password = pswdView.getText().toString();
        final String repassword = repswdView.getText().toString();

        boolean isPass = true;
        if (name.isEmpty()) {
            nameView.setError("请输入姓名");
            isPass = false;
        } else {
            nameView.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailView.setError("错误的邮箱格式");
            isPass = false;
        } else {
            emailView.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            phoneView.setError("错误的手机格式");
            isPass = false;
        } else {
            phoneView.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            pswdView.setError("请至少填写6位密码");
            isPass = false;
        } else {
            pswdView.setError(null);
        }

        if (repassword.isEmpty() || repassword.length() < 6 || !(repassword.equals(password))) {
            repswdView.setError("密码错误");
            isPass = false;
        } else {
            repswdView.setError(null);
        }

        return isPass;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
