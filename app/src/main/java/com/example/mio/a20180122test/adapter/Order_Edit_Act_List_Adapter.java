package com.example.mio.a20180122test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mio.a20180122test.R;
import com.example.mio.a20180122test.data.Order_Act_Point;

import java.util.ArrayList;

/**
 * Created by mio on 2018/1/27.
 */

public class Order_Edit_Act_List_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Order_Act_Point> my_order_act_list;
    public Order_Edit_Act_List_Adapter(Context context,ArrayList<Order_Act_Point> my_order_act_list) {//建構式裡的兩個參數代表要新增這個物件必須要給這兩個類型的參數
        this.context = context;
        this.my_order_act_list = my_order_act_list;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if(view==null){
                LayoutInflater inflater = LayoutInflater.from(context);
                view = inflater.inflate(R.layout.order_edit_act_listview, null);
                viewHolder = new Order_Edit_Act_List_Adapter.ViewHolder();
                viewHolder.tv1 = view.findViewById(R.id.order_edit_act);

                view.setTag(viewHolder);
            }
            else
            {
                viewHolder=(Order_Edit_Act_List_Adapter.ViewHolder)view.getTag();
            }

            viewHolder.tv1.setText(my_order_act_list.get(i).Order_Act);


        return view;
    }


    static class ViewHolder{
        TextView tv1;

    }
}
