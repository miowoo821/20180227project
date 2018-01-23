package com.example.mio.a20180122test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mio.a20180122test.data.Activities;

import java.util.ArrayList;

/**
 * Created by mio on 2018/1/23.
 */

public class ActlistAdapter extends BaseAdapter {
    Context context;
    ArrayList<Activities> my_act_list;

    public ActlistAdapter(Context context,ArrayList<Activities> my_act_list){//建構式裡的兩個參數代表要新增這個物件必須要給這兩個類型的參數
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

        ViewHolder viewHolder;
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
        viewHolder.tv1.setText(my_act_list.get(i).Activity_Name);
       viewHolder.tv2.setText(String.valueOf(my_act_list.get(i).Activity_F_Limited));
       viewHolder.tv3.setText(String.valueOf(my_act_list.get(i).Activity_F_Limited));

        return view;
    }
    static class ViewHolder{
        TextView tv1;
        TextView tv2;
        TextView tv3;

    }
}
