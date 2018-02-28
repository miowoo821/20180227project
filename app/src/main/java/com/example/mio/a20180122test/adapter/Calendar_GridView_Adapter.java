package com.example.mio.a20180122test.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mio.a20180122test.R;
import com.example.mio.a20180122test.helpler.Calendar_Helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by mio on 2018/2/26.
 */

public class Calendar_GridView_Adapter extends BaseAdapter {
    int i,i2;
    ArrayList day_Block;
    int year;
    int month;
    int day;
    Activity activity;
    Calendar calendar;
    public Calendar_GridView_Adapter(int year,int month,int day){
        this.year=year;
        this.month=month;
        this.day=day;
    }
//    public Calendar_GridView_Adapter(Activity activity,Calendar calendar){
//this.activity=activity;
//this.calendar=calendar;
//    }





    @Override
    public int getCount() {
       return 42;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        Calendar_GridView_Holder calendarGridViewHolder;
//
//        if(view==null){
//            calendarGridViewHolder=new Calendar_GridView_Holder(viewGroup.getContext());
//            view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.calendar_item,viewGroup,false);
//            calendarGridViewHolder.textViewArray[0]=(TextView)view.findViewById(R.id.day_block_0_0);
//            calendarGridViewHolder.textViewArray[1]=(TextView)view.findViewById(R.id.day_block_0_1);
//            calendarGridViewHolder.textViewArray[2]=(TextView)view.findViewById(R.id.day_block_0_2);
//            calendarGridViewHolder.textViewArray[3]=(TextView)view.findViewById(R.id.day_block_1_0);
//            calendarGridViewHolder.textViewArray[4]=(TextView)view.findViewById(R.id.day_block_1_1);
//            calendarGridViewHolder.textViewArray[5]=(TextView)view.findViewById(R.id.day_block_1_2);
//
//            view.setTag(calendarGridViewHolder);
//        }
//        else {
//            calendarGridViewHolder=(Calendar_GridView_Holder)view.getTag();
//        }
//
//        return view;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Calendar_GridView_Holder calendarGridViewHolder;

        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_item, parent, false);

            calendarGridViewHolder = new Calendar_GridView_Holder(convertView.getContext());
            calendarGridViewHolder.textViewArray[0] = (TextView) convertView.findViewById(R.id.day_block_0_0);
            calendarGridViewHolder.textViewArray[1] = (TextView) convertView.findViewById(R.id.day_block_0_1);
            calendarGridViewHolder.textViewArray[2] = (TextView) convertView.findViewById(R.id.day_block_0_2);
            calendarGridViewHolder.textViewArray[3] = (TextView) convertView.findViewById(R.id.day_block_1_0);
            calendarGridViewHolder.textViewArray[4] = (TextView) convertView.findViewById(R.id.day_block_1_1);
            calendarGridViewHolder.textViewArray[5] = (TextView) convertView.findViewById(R.id.day_block_1_2);
            convertView.setTag(calendarGridViewHolder);
//            TextView textView = (TextView) convertView.findViewById(R.id.day_block_0_1);
//            TextView textView2 = (TextView) convertView.findViewById(R.id.day_block_0_0);
//            TextView textView3 = (TextView) convertView.findViewById(R.id.day_block_0_2);
//            TextView textView4 = (TextView) convertView.findViewById(R.id.day_block_1_0);
//            TextView textView5 = (TextView) convertView.findViewById(R.id.day_block_1_1);
//            TextView textView6 = (TextView) convertView.findViewById(R.id.day_block_1_2);
            // textView.setText(String.valueOf(position));
        }else {
            calendarGridViewHolder=(Calendar_GridView_Holder) convertView.getTag();
        }

            int feb_days;//2月天數
            Calendar_Helper calendarHelper;
            calendarHelper =new Calendar_Helper();

            int start_Day=calendarHelper.get_Day_of_the_week(year,month,day);

            feb_days=calendarHelper.set_feb_Day(year);
            int[] MONTH_LENGTH_LIST ={ 31, feb_days, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            Log.d("GGGG","feb_days========="+feb_days) ;
            if(position<start_Day){//寫入上個月的尾巴
                int daylast=start_Day-position-1;//取得上個月與這個月的日期差

                if(month==1){
                    calendarGridViewHolder.textViewArray[1].setText(String.valueOf(MONTH_LENGTH_LIST[11] - daylast));
                }else {
                    calendarGridViewHolder.textViewArray[1].setText(String.valueOf(MONTH_LENGTH_LIST[month-2] - daylast));
                }
            }

            if(MONTH_LENGTH_LIST[month-1]>i && position>=start_Day){//寫入本月的日期
                calendarGridViewHolder.textViewArray[1].setText(String.valueOf(i));
                    if(i==day){
                        calendarGridViewHolder.textViewArray[1].setBackgroundColor(0xff0000ff);
                        calendarGridViewHolder.textViewArray[1].setTextColor(0xffffffff);
                        calendarGridViewHolder.textViewArray[1].setTextSize(8);
                    }
                i++;
            }

            if(position-start_Day>= MONTH_LENGTH_LIST[month-1]){//當position大於這個月的結尾時，寫入下個月的頭
                calendarGridViewHolder.textViewArray[1].setText(String.valueOf(i2));
                i2++;
            }

        return convertView;
    }

    class Calendar_GridView_Holder {

        TextView[] textViewArray;

        public Calendar_GridView_Holder(Context context) {
            int textViewCount = 6;
            textViewArray = new TextView[textViewCount];
            for(int i = 0; i < textViewCount; i++) {
                textViewArray[i] = new TextView(context);
            }
        }
    }

}
