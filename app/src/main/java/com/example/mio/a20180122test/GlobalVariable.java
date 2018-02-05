package com.example.mio.a20180122test;

import android.app.Application;

/**
 * Created by Student on 2018/2/5.
 */

public class GlobalVariable extends Application {//記得去AndroidMainifest android:name=".GlobalVariable"
    private String GlobalVariable_User_Account="DEFAULT";     //要傳送的字串
    //修改 變數字串
    public void set_GlobalVariable_User_Account(String GlobalVariable_User_Account){
        this.GlobalVariable_User_Account = GlobalVariable_User_Account;
    }
    //顯示 變數字串
    public String get_GlobalVariable_User_Account() {
        return GlobalVariable_User_Account;
    }
}
