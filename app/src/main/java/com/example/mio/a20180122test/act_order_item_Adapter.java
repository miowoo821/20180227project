package com.example.mio.a20180122test;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mio.a20180122test.data.Activities;
import com.example.mio.a20180122test.data.Orders;

import java.util.ArrayList;

import static com.example.mio.a20180122test.MainActivity.Name_DAO;

/**
 * Created by mio on 2018/1/27.
 */

public class act_order_item_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Orders> my_act_list;
    public static Activity_Interface dao;

    public act_order_item_Adapter(Context context,ArrayList<Orders> my_act_list){//建構式裡的兩個參數代表要新增這個物件必須要給這兩個類型的參數
        this.context=context;
        this.my_act_list=my_act_list;

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

        act_order_item_Adapter.ViewHolder viewHolder;
//        String DatabaseName=Name_DAO.get_account("DEFAULT");//DEFAULT之後再來修改
        dao=new Activities_DAO_DB_Impl(context);//直接把外面傳入的context當作參數再傳過去Activities_DAO_DB_Impl
        if(view==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.act_order_item, null);
            viewHolder = new act_order_item_Adapter.ViewHolder();
            viewHolder.tv1 = view.findViewById(R.id.act_order_item_date);
            viewHolder.tv2 = view.findViewById(R.id.act_order_item_account);
            viewHolder.tv3 = view.findViewById(R.id.act_order_item_normal_point);
            viewHolder.tv4 = view.findViewById(R.id.act_order_item_memo);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder=(act_order_item_Adapter.ViewHolder)view.getTag();
        }
        viewHolder.tv1.setText(my_act_list.get(i).Order_Date);
        viewHolder.tv2.setText(String.valueOf(my_act_list.get(i).Order_Account));
     //   viewHolder.tv3.setText(String.valueOf(my_act_list.get(i).Order_Normal_Point));
        viewHolder.tv4.setText(String.valueOf(my_act_list.get(i).Order_Memo));

        //若先抓第i個位置的view，在抓裡面的ID

        return view;
    }

    static class ViewHolder{
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;

    }
}
