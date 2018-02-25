package com.example.mio.a20180122test.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mio.a20180122test.Activities_DAO_DB_Impl;
import com.example.mio.a20180122test.Activity_Interface;
import com.example.mio.a20180122test.R;
import com.example.mio.a20180122test.data.Activities;

import java.util.ArrayList;

import static com.example.mio.a20180122test.MainActivity.Name_DAO;

/**
 * Created by mio on 2018/1/23.
 */

public class ActlistAdapter extends BaseAdapter {
    Context context;
    ArrayList<Activities> my_act_list;
    public static Activity_Interface dao;
    String GlobalVariable_User_Account;

    public ActlistAdapter(Context context,ArrayList<Activities> my_act_list,String GlobalVariable_User_Account){//建構式裡的兩個參數代表要新增這個物件必須要給這兩個類型的參數
        this.context=context;
        this.my_act_list=my_act_list;
        this.GlobalVariable_User_Account=GlobalVariable_User_Account;
    }

    @Override
    public int getCount() {
        return my_act_list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
       // String DatabaseName=Name_DAO.get_account("DEFAULT");//DEFAULT之後再來修改
        dao=new Activities_DAO_DB_Impl(context,GlobalVariable_User_Account);//直接把外面傳入的context當作參數再傳過去Activities_DAO_DB_Impl
        if(view==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.act_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tv1 = view.findViewById(R.id.AdapterActList_Name);
            viewHolder.tv2 = view.findViewById(R.id.AdapterActList_F_L);
            viewHolder.tv3 = view.findViewById(R.id.AdapterActList_F_N);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder)view.getTag();
        }
        Log.d("ADAPTER0", String.valueOf(viewHolder));
        viewHolder.tv1.setText(my_act_list.get(i).Activity_Name);
       viewHolder.tv2.setText(String.valueOf(my_act_list.get(i).Activity_F_Limited));


       //viewHolder.tv3.setText(String.valueOf(dao.get_act_now_point(dao.get_order_act_list().get(i).Order_Act_ID)));
        //上面這段程式碼以第i個viewHolder去抓第i個的dao.get_order_act_list，但第0個viewHolder不代表裝在裡面的
        //要比較是要拿活動列表的ID去比才對
       viewHolder.tv3.setText(String.valueOf(dao.get_act_now_point(GlobalVariable_User_Account,dao.get_activity_List().get(i)._id)));

        //若先抓第i個位置的view，在抓裡面的ID

//        Log.d("ADA", String.valueOf(i));
//        Log.d("ADAPTER0", String.valueOf(dao.get_order_act_list().get(0).Order_ID));
//        Log.d("ADAPTER1", String.valueOf(dao.get_order_act_list().get(1).Order_ID));
//        Log.d("ADAPTER2", String.valueOf(dao.get_order_act_list().get(2).Order_ID));
//        Log.d("ADAPTER", String.valueOf(dao.get_order_act_list().get(i).Order_Act_ID));
        return view;
    }
    static class ViewHolder{
        TextView tv1;
        TextView tv2;
        TextView tv3;
    }

}
