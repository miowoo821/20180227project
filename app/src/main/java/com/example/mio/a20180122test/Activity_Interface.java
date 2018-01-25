package com.example.mio.a20180122test;

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
//------------------------------------------------------------------------------
    public boolean add_order_act(Order_Act_Point order_act_point);

//------------------------------------------------------------------------------
    public long add_order(Orders orders);
    public ArrayList<Orders> get_order_List_filter(int date);
    public ArrayList<Orders> get_order_List();
    public Activities get_order(int _id);
    public boolean update_order(Orders orders );
    public boolean delete_order(int _id);
}
