package com.qindadai.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qindadai.latte.app.AccountManager;
import com.qindadai.latte.ec.database.DatabaseManager;
import com.qindadai.latte.ec.database.UserProfile;

/**
 * Created by mymac on 2019-05-24.
 * func:
 */
public class SignHandler {

    public static void onSignIn(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gendar = profileJson.getString("gendar");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gendar, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //注册完了并登录
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();

    }

    public static void onSignUp(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gendar = profileJson.getString("gendar");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gendar, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //注册完了并登录
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();

    }
}
