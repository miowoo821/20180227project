package com.example.mio.a20180122test;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.mio.a20180122test.data.Activities;
import com.example.mio.a20180122test.data.Orders;

import java.util.ArrayList;

/**
 * Created by mio on 2018/1/25.
 */

public class Order_Act_List_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Activities> my_order_act_list;
    boolean chks[];
    public Order_Act_List_Adapter(Context context,ArrayList<Activities> my_order_act_list,boolean chks[]){//建構式裡的兩個參數代表要新增這個物件必須要給這兩個類型的參數
        this.context=context;
        this.my_order_act_list=my_order_act_list;
        this.chks = chks;
    }
    @Override
    public int getCount() {
        return my_order_act_list.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
    ViewHolder viewHolder;
        if(view==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.new_order_act_list, null);
            viewHolder = new ViewHolder();
            viewHolder.tv1 = view.findViewById(R.id.order_act_list_tv);
            viewHolder.chk = (CheckBox) view.findViewById(R.id.checkBox);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder)view.getTag();
        }
        Log.d("TEST",String.valueOf(my_order_act_list.get(i).Activity_Name));
        Log.d("TEST",String.valueOf(my_order_act_list.get(i)));
        viewHolder.tv1.setText(my_order_act_list.get(i).Activity_Name);

        viewHolder.chk.setOnCheckedChangeListener(null);
        viewHolder.chk.setChecked(chks[i]);
        viewHolder.chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                chks[i] = b;
            }
        });

        return view;
    }
    static class ViewHolder{
        TextView tv1;
        CheckBox chk;
    }
}
