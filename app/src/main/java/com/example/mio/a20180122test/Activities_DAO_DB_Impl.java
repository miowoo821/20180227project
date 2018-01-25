package com.example.mio.a20180122test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mio.a20180122test.data.Activities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
    public ArrayList<Activities> get_activity_List_filter(int date){
        ArrayList<Activities> filter_act_list=new ArrayList<>();


        Cursor c = db.rawQuery("select * from Activities_list where Activity_S_D<="+date+" and Activity_E_D>="+date,null);


//        Cursor c=db.query("Activities_list", new String[] {
//                "_id", "Activity_Name", "Activity_S_D","Activity_E_D","Activity_F_S_D","Activity_F_E_D","Activity_F_Limited","Activity_F_Ratio","Activity_Memo"},
//                null,null, null, null, "_id DESC");
//
        if(c.moveToFirst()){
            Activities s1=new Activities(c.getInt(0),c.getString(1),c.getInt(2),c.getInt(3),c.getString(4),c.getString(5),c.getInt(6),c.getInt(7),c.getString(8));
            filter_act_list.add(s1);
            while (c.moveToNext()){
                Activities s=new Activities(c.getInt(0),c.getString(1),c.getInt(2),c.getInt(3),c.getString(4),c.getString(5),c.getInt(6),c.getInt(7),c.getString(8));
                filter_act_list.add(s);
            }
        }

        return filter_act_list;
    }
    @Override
    public ArrayList<Activities> get_activity_List( ) {
//SQL語法篩選
//        ArrayList<Activities> my_act_list=new ArrayList<>();
//        Cursor c = db.rawQuery("select * from Activities_list where Activity_S_D<20180105 and Activity_E_D>20180105",null);

        ArrayList<Activities> my_act_list=new ArrayList<>();
        Cursor c=db.query("Activities_list", new String[] {
                "_id", "Activity_Name", "Activity_S_D","Activity_E_D","Activity_F_S_D","Activity_F_E_D","Activity_F_Limited","Activity_F_Ratio","Activity_Memo"},
                null,null, null, null, "_id DESC");

        if(c.moveToFirst()){
            Activities s1=new Activities(c.getInt(0),c.getString(1),c.getInt(2),c.getInt(3),c.getString(4),c.getString(5),c.getInt(6),c.getInt(7),c.getString(8));
            my_act_list.add(s1);
            while (c.moveToNext()){
                Activities s=new Activities(c.getInt(0),c.getString(1),c.getInt(2),c.getInt(3),c.getString(4),c.getString(5),c.getInt(6),c.getInt(7),c.getString(8));
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
            Activities activities = new Activities(c.getInt(0), c.getString(1), c.getInt(2),c.getInt(3),c.getString(4),c.getString(5),c.getInt(6),c.getInt(7),c.getString(8));
            return activities;
        }
        return null;
    }
    @Override
    public boolean update_activity(Activities activities) {

        ContentValues cv = new ContentValues();
        cv.put("Activity_Name", activities.Activity_Name);
        cv.put("Activity_S_D", activities.Activity_S_D);
        cv.put("Activity_E_D", activities.Activity_E_D);
        cv.put("Activity_F_S_D", activities.Activity_F_S_D);
        cv.put("Activity_F_E_D", activities.Activity_F_E_D);
        cv.put("Activity_F_Limited", activities.Activity_F_Limited);
        cv.put("Activity_F_Ratio", activities.Activity_F_Ratio);
        cv.put("Activity_Memo", activities.Activity_Memo);
        db.update("Activities_list", cv, "_id=?", new String[] {String.valueOf(activities._id)});//
        return true;
    }
    @Override
    public boolean delete_activity(int _id) {
        db.delete("Activities_list", "_id=?", new String[] {String.valueOf(_id)});
        return true;
    }

    @Override
    public boolean add_order(Activities activities) {
        return false;
    }

    @Override
    public ArrayList<Activities> get_order_List_filter(int date) {
        return null;
    }

    @Override
    public ArrayList<Activities> get_order_List() {
        return null;
    }

    @Override
    public Activities get_order(int _id) {
        return null;
    }

    @Override
    public boolean update_order(Activities activities) {
        return false;
    }

    @Override
    public boolean delete_order(int _id) {
        return false;
    }

}
