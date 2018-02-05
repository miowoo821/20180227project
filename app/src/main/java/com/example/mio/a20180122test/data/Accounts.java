package com.example.mio.a20180122test.data;

import android.accounts.Account;

/**
 * Created by Student on 2018/2/5.
 */

public class Accounts {
    public int ID;
    public String Account_Name;

    public Accounts(int ID ,String account_Name){
        this.ID=ID;
        this.Account_Name=account_Name;
    }
    public Accounts(String account_Name){
        this.Account_Name=account_Name;
    }
}
