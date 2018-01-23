package com.example.mio.a20180122test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mio.a20180122test.data.Activities;

import java.util.ArrayList;

/**
 * Created by mio on 2018/1/22.
 */

public class Activities_DAO_DB_Impl implements Activity_Interface {
    Context context;
    SQLiteDatabase db;

    public Activities_DAO_DB_Impl(Context context){
        this.context=context;
        My_DB_Helper my_db_helper=new My_DB_Helper(context);
        db=my_db_helper.getWritableDatabase();


    }
    @Override
    public boolean add(Activities activities) {
        ContentValues cv=new ContentValues();
        cv.put("Activity_Name",activities.Activity_Name);
        cv.put("Activity_S_D",activities.Activity_S_D);
        cv.put("Activity_E_D",activities.Activity_E_D);
        cv.put("Activity_F_S_D",activities.Activity_F_S_D);
        cv.put("Activity_F_E_D",activities.Activity_F_E_D);
        cv.put("Activity_F_Limited",activities.Activity_F_Limited);
        cv.put("Activity_F_Ratio",activities.Activity_F_Ratio);
        cv.put("Activity_Memo",activities.Activity_Memo);
        db.insert("Activities_list",null,cv);
        return true;
    }

    @Override
    public ArrayList<Activities> get_activity_List( ) {

        ArrayList<Activities> my_act_list=new ArrayList<>();

        Cursor c=db.query("Activities_list", new String[] {
                "_id", "Activity_Name", "Activity_S_D","Activity_E_D","Activity_F_S_D","Activity_F_E_D","Activity_F_Limited","Activity_F_Ratio","Activity_Memo"},
                null, null, null, null, null);

        if(c.moveToFirst()){
            Activities s1=new Activities(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getInt(6),c.getInt(7),c.getString(8));
            my_act_list.add(s1);
            while (c.moveToNext()){
                Activities s=new Activities(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getInt(6),c.getInt(7),c.getString(8));
                my_act_list.add(s);
            }
        }
        return my_act_list;
    }

    @Override
    public Activities get_activity(int id) {
        Cursor c=db.query("Activities_list", new String[] {"_id", "Activity_Name", "Activity_S_D","Activity_E_D","Activity_F_S_D","Activity_F_E_D","Activity_F_Limited","Activity_F_Ratio","Activity_Memo"}
                , "_id=?", new String[]{String.valueOf(id)}, null, null, null);
        if(c.moveToFirst()) {
            Activities activities = new Activities(c.getInt(0), c.getString(1), c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getInt(6),c.getInt(7),c.getString(8));
            return activities;
        }
        return null;
    }

    @Override
    public boolean update_activity(Activities activities) {
        return false;
    }

    @Override
    public boolean delete_activity(int _id) {
        return false;
    }
}
