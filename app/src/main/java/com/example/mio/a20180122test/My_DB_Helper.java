package com.example.mio.a20180122test;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mio.a20180122test.data.My_DB_Helper_account;

/**
 * Created by mio on 2018/1/22.
 */

public class My_DB_Helper extends SQLiteOpenHelper {

    static String databasename="Default0";
    final static String DB_NAME = databasename+".sqlite";
    final static int VERSION = 1;
    Context context;

    public My_DB_Helper(Context context ) {

        super(context, DB_NAME, null, VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table User_list(_id integer primary key autoincrement,"+
                "User_Name text not null UNIQUE)");
        ContentValues cv=new ContentValues();
        cv.put("User_Name","DEFAULT");
        sqLiteDatabase.insert("User_list",null,cv);
//       E/SQLiteLog: (5) database is locked
//                在資料庫oncreate的時候有大問題囉
//        預設資料可以直接用參數做，不需要再重頭建一個自己做

        sqLiteDatabase.execSQL("create table Activities_list(_id integer primary key autoincrement,"+
                "Activity_Name text not null,"+
                "Activity_S_D int not null,"+
                "Activity_E_D int not null,"+
                "Activity_F_S_D int not null,"+
                "Activity_F_E_D int not null,"+
                "Activity_F_Limited integer not null,"+
                "Activity_F_Ratio integer not null,"+
                "Activity_Memo text )");
        sqLiteDatabase.execSQL("create table Order_list(_id integer primary key autoincrement,"+
                "User_Name_ID text not null,"+
                "Order_Date text not null,"+
                "Order_Account integer not null,"+
             //   "Order_Normal_Point integer not null,"+
                "Order_Memo text)");
        sqLiteDatabase.execSQL("create table Order_ActPoint_list(_id integer primary key autoincrement,"+
                "Order_ID_ text not null,"+
                "Order_Act_ID int not null,"+
                "Order_Act text not null,"+
                "Order_Act_Point integer not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
