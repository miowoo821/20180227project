package com.example.mio.a20180122test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mio on 2018/1/22.
 */

public class My_DB_Helper extends SQLiteOpenHelper {
    final static String DB_NAME = "Activities4.sqlite";
    final static int VERSION = 1;

    public My_DB_Helper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Activities_list(_id integer primary key autoincrement,"+
                "Activity_Name text not null,"+
                "Activity_S_D int not null,"+
                "Activity_E_D int not null,"+
                "Activity_F_S_D text not null,"+
                "Activity_F_E_D text not null,"+
                "Activity_F_Limited integer not null,"+
                "Activity_F_Ratio integer not null,"+
                "Activity_Memo text )");
        sqLiteDatabase.execSQL("create table Order_list(_id integer primary key autoincrement,"+
                "Order_Date text not null,"+
                "Order_Account integer not null,"+
                "Order_Normal_Point integer not null,"+
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
