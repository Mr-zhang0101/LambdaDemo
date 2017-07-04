package com.zhang.lambdademo;

import android.util.Log;
import android.view.View;

/**
 * Project: LambdaDemo
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/7/3
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class User {
    private static final String TAG = "USER";
    private String userName;

    public User(View view) {
        Log.d(TAG, "method: 构造方法");
    }
    public static void sMethod(View view){
        Log.d(TAG, "method: 静态方法");
    }
    public void mMethod(View view){
        Log.d(TAG, "method: 成员方法");
    }

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
