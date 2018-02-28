package com.example.mio.a20180122test.fragment;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mio.a20180122test.R;
import com.example.mio.a20180122test.adapter.Calendar_GridView_Adapter;
import com.example.mio.a20180122test.helpler.Calendar_Helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Calendar_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Calendar_Fragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String fix_year="今年" ;
    private static final String fix_month="本月" ;
    private static  final String fix_day="本日" ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
//
    int year;
    int month;
    int day;
//    TextView display_mounth;

    View view;
    Context context;
    //****************************給GridView產生一整頁************
//    public static int item_grid_num = 42;//這個fragment裡面總共要有幾個GridView
//    public static int number_columns = 7;//控制一行裡面有幾個GridView
    int item_grid_num = 42;
    int number_columns = 7;

    Calendar_GridView_Adapter calendarGridViewAdapter;

    //*************************************************************
    public Calendar_Fragment(){

    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Calendar_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Calendar_Fragment newInstance(String param1, String param2,int year,int month,int day) {
        Calendar_Fragment fragment = new Calendar_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(fix_year, String.valueOf(year));
        args.putString(fix_month, String.valueOf(month));
        args.putString(fix_day, String.valueOf(day));
        fragment.setArguments(args);



        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            year=Integer.valueOf(getArguments().getString(fix_year)) ;
            month=Integer.valueOf(getArguments().getString(fix_month)) ;
            day=Integer.valueOf(getArguments().getString(fix_day)) ;

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar_, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        view=getView();
        context=getContext();
        ArrayList day_BlockList;



//        //*************************抓時間********************************
//        Calendar calendar;
//
//        calendar=new GregorianCalendar();
//        year=calendar.get(Calendar.YEAR) ;
//        month=calendar.get(Calendar.MONTH)+1 ;//0~11補正1~12
//        day=calendar.get(Calendar.DAY_OF_MONTH) ;
//        display_mounth=(TextView)view.findViewById(R.id.display_month);
//        display_mounth.setText(String.valueOf(year+"年"+month+"月"));
//
//        //*****************************************************************
        Calendar_Helper calendarHelper;
        calendarHelper =new Calendar_Helper();

        calendarHelper.get_Day_of_the_week(year,month,day);

        Calendar_GridView_Adapter calendarGridViewAdapter=new Calendar_GridView_Adapter(year,month,day);
//        Calendar_GridView_Adapter calendarGridViewAdapter=new Calendar_GridView_Adapter(view.getContext(),getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);

        GridView calendarGridView=(GridView)view.findViewById(R.id.calendar_gridview);
        calendarGridView.setAdapter(calendarGridViewAdapter);
        calendarGridView.setNumColumns(7);

    }

    @Override
    public void onResume() {
        super.onResume();
//        display_mounth.setText(String.valueOf(year+"年"+month+"月"));
    }

}
