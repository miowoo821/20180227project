package com.example.mio.a20180122test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.TextView;

import com.example.mio.a20180122test.data.Activities;
import com.example.mio.a20180122test.data.Order_Act_Point;
import com.example.mio.a20180122test.data.Orders;

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
    public SQLiteDatabase db;
   String GlobalVariable_User_Account;

    public Activities_DAO_DB_Impl(Context context,String GlobalVariable_User_Account){
        this.GlobalVariable_User_Account=GlobalVariable_User_Account;
        this.context=context;

//        My_DB_Helper my_db_helper=new My_DB_Helper(context);
//        db=my_db_helper.getWritableDatabase();
    }

    //----------------------Activities.java---------------------------------------------------------
    @Override
    public boolean add(Activities activities) {

        My_DB_Helper my_db_helper=new My_DB_Helper(context,GlobalVariable_User_Account);
        db=my_db_helper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Activity_Name",activities.Activity_Name);
        cv.put("Activity_S_D",activities.Activity_S_D);
        cv.put("Activity_E_D",activities.Activity_E_D);
        cv.put("Activity_F_S_D",activities.Activity_F_S_D);
        cv.put("Activity_F_E_D",activities.Activity_F_E_D);
        cv.put("Activity_F_Limited",activities.Activity_F_Limited);
        cv.put("Activity_F_Ratio",activities.Activity_F_Ratio);
        cv.put("Activity_Memo",activities.Activity_Memo);
        long id=db.insert("Activities_list",null,cv);
        //加long可以取的最新插入的主鍵欸，不加也可以執行，只是不知道怎麼抓主鍵(原碼db.insert("Activities_list",null,cv);)
        db.close();
        return true;
    }
    @Override//以日期作為篩選活動的條件，新增訂單或修改訂單時可自動依據訂單時間跳出符合的活動
    public ArrayList<Activities> get_activity_List_filter(int date){
        My_DB_Helper my_db_helper=new My_DB_Helper(context,GlobalVariable_User_Account);
        db=my_db_helper.getWritableDatabase();
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
        db.close();
        return filter_act_list;
    }
    @Override
    public ArrayList<Activities> get_activity_List( ) {
//SQL語法篩選
//        ArrayList<Activities> my_act_list=new ArrayList<>();
//        Cursor c = db.rawQuery("select * from Activities_list where Activity_S_D<20180105 and Activity_E_D>20180105",null);
        My_DB_Helper my_db_helper=new My_DB_Helper(context,GlobalVariable_User_Account);
        db=my_db_helper.getWritableDatabase();
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
        db.close();
        return my_act_list;
    }
    @Override
    public Activities get_activity(int id) {
        My_DB_Helper my_db_helper=new My_DB_Helper(context,GlobalVariable_User_Account);
        db=my_db_helper.getWritableDatabase();
        Cursor c=db.query("Activities_list", new String[] {"_id", "Activity_Name", "Activity_S_D","Activity_E_D","Activity_F_S_D","Activity_F_E_D","Activity_F_Limited","Activity_F_Ratio","Activity_Memo"}
                , "_id=?", new String[]{String.valueOf(id)}, null, null, null);
        if(c.moveToFirst()) {
            Activities activities = new Activities(c.getInt(0), c.getString(1), c.getInt(2),c.getInt(3),c.getString(4),c.getString(5),c.getInt(6),c.getInt(7),c.getString(8));
            return activities;
        }
        db.close();
        return null;
    }
    @Override
    public boolean update_activity(Activities activities) {
        My_DB_Helper my_db_helper=new My_DB_Helper(context,GlobalVariable_User_Account);
        db=my_db_helper.getWritableDatabase();
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
        db.close();
        return true;
    }
    @Override
    public boolean delete_activity(int _id) {
        My_DB_Helper my_db_helper=new My_DB_Helper(context,GlobalVariable_User_Account);
        db=my_db_helper.getWritableDatabase();
        db.delete("Activities_list", "_id=?", new String[] {String.valueOf(_id)});
        db.delete("Order_ActPoint_list", "Order_Act_ID=?", new String[] {String.valueOf(_id)});

        db.close();
        return true;
    }
    @Override//作活動目前獲得點數的加總
    public int get_act_now_point(int Order_Act_ID_1) {
        My_DB_Helper my_db_helper=new My_DB_Helper(context,GlobalVariable_User_Account);
        db=my_db_helper.getWritableDatabase();
//刪        第一個參數從外面傳入一個作為篩選條件的欄位，第二個參數是從外面傳入一個要操作加總的欄位，第三個參數是決定要抓出哪一筆資料的點數加總(只留下某筆資料)

//        Cursor c= db.rawQuery(
//                "SELECT Order_Act_ID, SUM(Order_Act_Point) FROM Order_ActPoint_list GROUP BY Order_Act_ID ",null);
//        //語法意思：從Order_ActPoint_list依照Order_Act_ID對Order_Act_Point作加總，並把加總過後的欄位命名為SUM(Order_Act_Point)
//        //執行完上面的語法會得到一張兩個欄位的表
//        // 第一欄是GROUP BY後的Order_Act_ID組成(Order_Act_ID不重複的表)，第二欄是依照Order_Act_ID對act_now_point作加總產生的的欄位(多個Order_Act_ID的act_now_point加總成一筆)
//        //然後只有兩欄，一欄篩選條件的欄位，另一欄是條件加總的欄位
//        //並做篩選只留下與Order_Act_ID符合的資料(只有一筆)
//        c.moveToFirst();
//        for(int i=0;i<c.getCount();i++){
//            if( String.valueOf(c.getInt(0))!= String.valueOf(Order_Act_ID_1)) {
//                c.moveToNext();
//            }
//        }
        Cursor c= db.rawQuery(
                "SELECT Order_ID_ ,Order_Act_ID FROM Order_ActPoint_list where Order_Act_ID="+Order_Act_ID_1,null);

        int total_point=0;
        c.moveToFirst();
        for (int i=0;i<c.getCount();i++){
            if(  c.getString(1).equals(String.valueOf(Order_Act_ID_1)    )) {
              total_point=total_point+ ( get_order(Integer.valueOf(c.getInt(0))).Order_Account) * (get_activity(Order_Act_ID_1).Activity_F_Ratio)/100;
                //不是c.getInt(i)，c.getInt是抓第幾欄，不是第幾筆
            }
            c.moveToNext();
        }
        db.close();
        if (total_point>get_activity(Order_Act_ID_1).Activity_F_Limited){
            return get_activity(Order_Act_ID_1).Activity_F_Limited;
        }
        else {
            return total_point;
        }

    }
    @Override
    public Cursor get_activity_date(int date) {
        My_DB_Helper my_db_helper=new My_DB_Helper(context,GlobalVariable_User_Account);
        db=my_db_helper.getWritableDatabase();
        //ArrayList<Activities> get_activity_date=new ArrayList<>();
        Cursor c = db.rawQuery("select * from Activities_list where Activity_F_S_D<="+date+" and Activity_F_E_D>="+date,null);
//這邊不能close，因為要回傳Cursor,要在外面的Cursor用完之後才能關
        return c;
    }

    //----------Order_Act_Point.java--------------------------------------------------------------------
    @Override//新增訂單以及修改訂單時都會取用訂單活動表的新增功能
    public boolean add_order_act(Order_Act_Point order_act_point) {
        My_DB_Helper my_db_helper=new My_DB_Helper(context,GlobalVariable_User_Account);
        db=my_db_helper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Order_ID_",order_act_point.Order_ID);
        cv.put("Order_Act_ID",order_act_point.Order_Act_ID);
        cv.put("Order_Act",order_act_point.Order_Act);
        cv.put("Order_Act_Point",order_act_point.Order_Act_Point);
        db.insert("Order_ActPoint_list",null,cv);
        db.close();
        return  true;
    }
    @Override
    public boolean update_order_act(Order_Act_Point order_act_point) {
//        ContentValues cv=new ContentValues();
//        cv.put("Order_ID_",order_act_point.Order_ID);
//        cv.put("Order_Act_ID",order_act_point.Order_Act_ID);
//        cv.put("Order_Act",order_act_point.Order_Act);
//        cv.put("Order_Act_Point",order_act_point.Order_Act_Point);
//        db.update("Order_ActPoint_list", cv, "_id=?", new String[] {String.valueOf(order_act_point)});
        My_DB_Helper my_db_helper=new My_DB_Helper(context,GlobalVariable_User_Account);
        db=my_db_helper.getWritableDatabase();
        db.close();
        return  true;
    }
    @Override//以訂單ID刪除資料(訂單修改及訂單刪除時使用)
    public boolean delete_order_act(int id) {
        My_DB_Helper my_db_helper=new My_DB_Helper(context,GlobalVariable_User_Account);
        db=my_db_helper.getWritableDatabase();
        db.delete("Order_ActPoint_list", "Order_ID_=?", new String[] {String.valueOf(id)});
        db.close();
        return true;
    }
    @Override//得到訂單活動表
    public ArrayList<Order_Act_Point> get_order_act_list() {

        My_DB_Helper my_db_helper=new My_DB_Helper(context,GlobalVariable_User_Account);
        db=my_db_helper.getWritableDatabase();
        ArrayList<Order_Act_Point> my_act_list=new ArrayList<>();
        Cursor c=db.query("Order_ActPoint_list", new String[] {
                        "_id", "Order_ID_", "Order_Act_ID","Order_Act","Order_Act_Point"},
                null,null, null, null, "_id DESC");

        if(c.moveToFirst()){
            Order_Act_Point s1=new Order_Act_Point(c.getInt(0),c.getLong(1),c.getInt(2),c.getString(3),c.getInt(4));
            my_act_list.add(s1);
            while (c.moveToNext()){
                Order_Act_Point s=new Order_Act_Point(c.getInt(0),c.getLong(1),c.getInt(2),c.getString(3),c.getInt(4));
                my_act_list.add(s);
            }
        }
        db.close();
        return my_act_list;
    }
    @Override//篩選該ID所符合的活動出來
    public ArrayList<Order_Act_Point> get_act_order_List_filter(int id) {
        My_DB_Helper my_db_helper=new My_DB_Helper(context,GlobalVariable_User_Account);
        db=my_db_helper.getWritableDatabase();
        ArrayList<Order_Act_Point> get_order_List_filter=new ArrayList<>();

        Cursor c = db.rawQuery("select * from Order_ActPoint_list where Order_ID_="+id,null);
//        Cursor c=db.query("Activities_list", new String[] {
//                "_id", "Activity_Name", "Activity_S_D","Activity_E_D","Activity_F_S_D","Activity_F_E_D","Activity_F_Limited","Activity_F_Ratio","Activity_Memo"},
//                null,null, null, null, "_id DESC");
//
        if(c.moveToFirst()){
            Order_Act_Point s1=new Order_Act_Point(c.getInt(0),c.getLong(1),c.getInt(2),c.getString(3),c.getInt(4));

            get_order_List_filter.add(s1);
            while (c.moveToNext()){
                Order_Act_Point s=new Order_Act_Point(c.getInt(0),c.getLong(1),c.getInt(2),c.getString(3),c.getInt(4));
                get_order_List_filter.add(s);
            }
        }
        db.close();
        return get_order_List_filter;
    }

//-----------------Orders.java----------------------------------------------------------------------

    @Override
    public long add_order(Orders orders) {
        My_DB_Helper my_db_helper=new My_DB_Helper(context,GlobalVariable_User_Account);
        db=my_db_helper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("User_Name_ID",orders.User_Name_ID);
        cv.put("Order_Date",orders.Order_Date);
        cv.put("Order_Account",orders.Order_Account);
       // cv.put("Order_Normal_Point",orders.Order_Normal_Point);
        cv.put("Order_Memo",orders.Order_Memo);
        long id=db.insert("Order_list",null,cv);//前面加個long 就可以用變數取得主鍵欸
        db.close();
        return  id;
    }
    @Override
    public ArrayList<Orders> get_order_List_filter(int id) {
        return null;
    }
    @Override//把order的資料全部抓出來
    public ArrayList<Orders> get_order_List() {
        My_DB_Helper my_db_helper=new My_DB_Helper(context,GlobalVariable_User_Account);
        db=my_db_helper.getWritableDatabase();
        ArrayList<Orders> my_act_list=new ArrayList<>();
        Cursor c=db.query("Order_list", new String[] {
                        "_id", "User_Name_ID","Order_Date", "Order_Account","Order_Memo"},
                "User_Name_ID=?",new String[]{GlobalVariable_User_Account}, null, null, "_id DESC");

        if(c.moveToFirst()){
            Orders s1=new Orders(c.getInt(0),c.getString(1),c.getString(2),c.getInt(3),c.getString(4));
            //從Cursor裡面抓參數給s1產生Orders物件
            my_act_list.add(s1);
            while (c.moveToNext()){
                Orders s=new Orders(c.getInt(0),c.getString(1),c.getString(2),c.getInt(3),c.getString(4));
                my_act_list.add(s);
            }
        }
        db.close();
        return my_act_list;
    }
    @Override
    public Orders get_order(int _id) {

        My_DB_Helper my_db_helper=new My_DB_Helper(context,GlobalVariable_User_Account);
        db=my_db_helper.getWritableDatabase();
        Cursor c=db.query("Order_list", new String[] {
                        "_id", "User_Name_ID","Order_Date", "Order_Account","Order_Memo"}
                , "_id=?", new String[]{String.valueOf(_id)}, null, null, null);
        if(c.moveToFirst()) {
            Orders orders = new Orders(c.getString(1),c.getString(2),c.getInt(3),c.getString(4));
            return orders;
        }
        db.close();
        return null;
    }
    @Override//修改訂單，同時牽一髮動全身的修改(刪除再新增)了訂單活動
    public boolean update_order(Orders orders, int id, int date, int point,boolean chks[]) {
////第一個參數用來修改訂單，第二個參數用來刪除訂單活動，第三個第四個參數來新增訂單活動資料表的兩個欄位(日期跟一般點數)，一般點數欄位其實可以刪掉拉，第五個參數是傳回勾勾的陣列，這樣才知道有幾個要跑
        My_DB_Helper my_db_helper=new My_DB_Helper(context,GlobalVariable_User_Account);
        db=my_db_helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Order_Date", orders.Order_Date);
        cv.put("Order_Account", orders.Order_Account);
       // cv.put("Order_Normal_Point", orders.Order_Normal_Point);
        cv.put("Order_Memo", orders.Order_Memo);
        db.update("Order_list", cv, "_id=?", new String[] {String.valueOf(orders._id_order)});//


        delete_order_act(get_order_List().get(id)._id_order);//刪除訂單活動的舊資料(記得還要在新增修改後的)
        for (int i1=0;i1<chks.length;i1++)
        {
            if (chks[i1])
            {
                add_order_act(new Order_Act_Point(//新增到訂單活動資料表
                        get_order_List().get(id)._id_order,
                        get_activity_List_filter(date).get(i1)._id,
                        get_activity_List_filter(date).get(i1).Activity_Name,
                        get_activity_List_filter(date).get(i1).Activity_F_Ratio*point
                ));
            }
        }
        db.close();
        return true;
    }
    @Override
    public boolean delete_order(int _id) {


        return false;
    }
//--------------------------------------------------------------------------------------------------
}
