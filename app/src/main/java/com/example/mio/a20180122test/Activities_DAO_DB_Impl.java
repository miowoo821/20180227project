package com.example.mio.a20180122test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.mio.a20180122test.Activities;
import com.example.mio.a20180122test.Activity_Interface;
import com.example.mio.a20180122test.My_DB_Helper;

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
    public SimpleCursorAdapter get_activity_List(Context context) {//context引數由外面給，這樣SimpleCursorAdapter就不用再現在給context
    this.context=context;
//        ArrayList<Activities> mylist=new ArrayList<>();//以SimpleCursorAdapter取代ArrayList


        Cursor c=db.query("Activities_list", new String[] {
                "_id", "Activity_Name", "Activity_S_D","Activity_E_D","Activity_F_S_D","Activity_F_E_D","Activity_F_Limited","Activity_F_Ratio","Activity_Memo"},
                null, null, null, null, null);
       c.moveToFirst();
        SimpleCursorAdapter  adapter=new SimpleCursorAdapter(context,R.layout.act_cursoradapter,c,
                new String[] {"_id", "Activity_Name", "Activity_S_D","Activity_E_D","Activity_F_S_D","Activity_F_E_D","Activity_F_Limited","Activity_F_Ratio","Activity_Memo"},
                new int[]{R.id.textView14,R.id.textView15,R.id.textView16,R.id.textView17,R.id.textView18,R.id.textView19,R.id.textView20,R.id.textView21,R.id.textView22},
                1);

//        if(c.moveToFirst()){
//            Activities s1=new Activities(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getInt(6),c.getInt(7),c.getString(8));
//            mylist.add(s1);
//            while (c.moveToNext()){
//                Activities s=new Activities(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getInt(6),c.getInt(7),c.getString(8));
//
//                mylist.add(s);
//            }
//        }
        Log.d("GHDGSAFFDSGD",String.valueOf(adapter.getCursor()));
        Log.d("GHDGSAFFDSGD",String.valueOf(adapter.areAllItemsEnabled()));
        return adapter;
    }

    @Override
    public Activities get_activity(int _id) {
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
