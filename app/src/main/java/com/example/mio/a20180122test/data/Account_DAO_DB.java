package com.example.mio.a20180122test.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.mio.a20180122test.My_DB_Helper;

/**
 * Created by Student on 2018/2/5.
 */

public class Account_DAO_DB {

    Context context;
    SQLiteDatabase db;
    public Account_DAO_DB(Context context){
        this.context=context;
    }

    public void add_account(Accounts accounts){
        My_DB_Helper_account account_dao_db=new My_DB_Helper_account(context);
        db=account_dao_db.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Account_Name",accounts.Account_Name);
        db.insert("Account_list",null,cv);
    }
    public String get_account(Accounts accounts){

        String ready_to_use_name=accounts.Account_Name;

        return ready_to_use_name;

    }
}
