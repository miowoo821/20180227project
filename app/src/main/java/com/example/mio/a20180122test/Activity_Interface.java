package com.example.mio.a20180122test;

import android.database.Cursor;
import android.text.TextWatcher;
import android.widget.TextView;

import com.example.mio.a20180122test.data.Activities;
import com.example.mio.a20180122test.data.Order_Act_Point;
import com.example.mio.a20180122test.data.Orders;

import java.util.ArrayList;

/**
 * Created by mio on 2018/1/22.
 */

public interface Activity_Interface {
    public boolean add(Activities activities);
    //public ArrayList<Activities> get_activity_List(Context context);//context引數由外面給，這樣SimpleCursorAdapter就不用再現在給context
    ////以SimpleCursorAdapter取代ArrayList
    public ArrayList<Activities> get_activity_List_filter(int date);
    public ArrayList<Activities> get_activity_List();
    public Activities get_activity(int _id);
    public boolean update_activity(Activities activities);
    public boolean delete_activity(int _id);
    public int get_act_now_point(int Order_Act_ID_1);
    //(刪)第一個參數從外面傳入一個作為篩選條件的欄位，第二個參數是從外面傳入一個要操作加總的欄位，第三個參數是決定要抓出哪一筆資料的點數加總
    public Cursor get_activity_date(int date);
//------------------------------------------------------------------------------
    public boolean add_order_act(Order_Act_Point order_act_point);
    public boolean update_order_act(Order_Act_Point order_act_point);
    public boolean delete_order_act(int order_id);//把符合order_id的資料刪掉
    public  ArrayList<Order_Act_Point> get_order_act_list();
    public ArrayList<Order_Act_Point> get_act_order_List_filter(int id);
//------------------------------------------------------------------------------
    public long add_order(Orders orders);
    public ArrayList<Orders> get_order_List_filter(int id);
    public ArrayList<Orders> get_order_List();
    public Orders get_order(int _id);
    public boolean update_order(Orders orders, int id, int date, int point,boolean chks[]);//第一個參數用來修改訂單，第二個參數用來刪除訂單活動，第三個第四個參數來新增訂單活動資料表的兩個欄位(日期跟一般點數)，一般點數欄位其實可以刪掉拉，第五個參數是傳回勾勾的陣列，這樣才知道有幾個要跑
    public boolean delete_order(int _id);
}
