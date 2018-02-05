package com.example.mio.a20180122test.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mio.a20180122test.My_DB_Helper;

/**
 * Created by Student on 2018/2/5.
 */

public class My_DB_Helper_account extends SQLiteOpenHelper {
    Context context;
    SQLiteDatabase db;
    final static String DB_NAME1= "Account.sqlite";
    final static int VERSION = 1;
    public My_DB_Helper_account(Context context){

        super(context,DB_NAME1,null,VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table Account_list(_id integer primary key autoincrement,"+
                "Account_Name text not null)");
        My_DB_Helper_account account_dao_db=new My_DB_Helper_account(context);
        db=account_dao_db.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Account_Name","DEFAULT");
        db.insert("Account_list",null,cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
