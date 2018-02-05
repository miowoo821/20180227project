package com.example.mio.a20180122test.data;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mio.a20180122test.GlobalVariable;
import com.example.mio.a20180122test.My_DB_Helper;

import java.util.ArrayList;

/**
 * Created by Student on 2018/2/5.
 */

public class Account_DAO_DB {
    String GlobalVariable_User_Account;
    Context context;
    SQLiteDatabase db;
    public Account_DAO_DB(Context context,String GlobalVariable_User_Account){
        this.context=context;
        this.GlobalVariable_User_Account=GlobalVariable_User_Account;
    }

    public void add_account(Accounts accounts){
//        My_DB_Helper_account account_dao_db=new My_DB_Helper_account(context);
//        db=account_dao_db.getWritableDatabase();
        My_DB_Helper account_dao_db=new My_DB_Helper(context,GlobalVariable_User_Account);
        db=account_dao_db.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put("User_Name",accounts.Account_Name);
        db.insert("User_list",null,cv);

        db.close();
    }
    public String get_account(String Account_Name){
        Log.d("FFFFFF","Account_Name================="+Account_Name);

        String ready_to_use_name;
        My_DB_Helper_account account_dao_db=new My_DB_Helper_account(context);
        db=account_dao_db.getWritableDatabase();
        Cursor c=db.rawQuery("select * from User_list where Account_Name="+Account_Name,null);
        ready_to_use_name=c.getString(1);

        db.close();
        return ready_to_use_name;

    }
    public ArrayList<Accounts> get_Account_list(){

        ArrayList A_List=new ArrayList();

        My_DB_Helper account_dao_db=new My_DB_Helper(context,GlobalVariable_User_Account);
        db=account_dao_db.getWritableDatabase();

        Cursor c=db.query("User_list",new String[]{
                "_id","User_Name"},
                null,null,null, null, null );
        if(c.moveToFirst()) {
            Accounts s1 = new Accounts(c.getInt(0) ,c.getString(1));
            A_List.add(s1);
            while (c.moveToNext()) {
                Accounts s = new Accounts(c.getInt(0) ,c.getString(1));
                A_List.add(s);
            }

        }
        db.close();
        return A_List;
    }

}
