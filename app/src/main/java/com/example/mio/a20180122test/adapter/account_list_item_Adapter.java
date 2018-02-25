package com.example.mio.a20180122test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mio.a20180122test.Activities_DAO_DB_Impl;
import com.example.mio.a20180122test.R;
import com.example.mio.a20180122test.data.Account_DAO_DB;
import com.example.mio.a20180122test.data.Accounts;
import com.example.mio.a20180122test.data.Orders;

import java.util.ArrayList;

/**
 * Created by mio on 2018/2/5.
 */

public class account_list_item_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Accounts> my_account_list;
   // public static Account_DAO_DB Adao;

    //**********兩者差異在哪********************
   Activities_DAO_DB_Impl dao;//這邊好像只能透過介面，不然要NEW不知道去哪NEW，建構式裡嗎?不知道(答：建構式裡可以new阿)
    //Activity_Interface dao;//介面到底是拿來幹嘛的
    //**********兩者差異在哪********************

    String GlobalVariable_User_Account;

    public  account_list_item_Adapter(Context context,ArrayList<Accounts> my_account_list,String GlobalVariable_User_Account){
        this.context=context;
        this.my_account_list=my_account_list;
        this.GlobalVariable_User_Account=GlobalVariable_User_Account;
       dao=new Activities_DAO_DB_Impl(context,GlobalVariable_User_Account);

    }
    @Override
    public int getCount() {
        return my_account_list.size();
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
//        Adao=new Account_DAO_DB(context);
//        Adao.get_Account_list();
        if(view==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.account_list_item, null);
            viewHolder=new ViewHolder();
            viewHolder.tv1=view.findViewById(R.id.textView32);
            viewHolder.tv2=view.findViewById(R.id.textView33);
            view.setTag(viewHolder);

        }else {
            viewHolder=(ViewHolder)view.getTag();
        }

        viewHolder.tv1.setText(my_account_list.get(i).Account_Name);
        viewHolder.tv2.setText(String.valueOf(dao.get_account_all_point(String.valueOf(my_account_list.get(i).Account_Name))));

        return view;
    }

    class ViewHolder{
        TextView tv1;
        TextView tv2;
    }
}
