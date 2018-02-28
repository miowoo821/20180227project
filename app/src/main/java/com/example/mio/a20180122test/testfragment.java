package com.example.mio.a20180122test;

import android.app.Fragment;

/**
 * Created by mio on 2018/2/28.
 */
//package tk.sweetvvck.calender.activity;
import java.util.Calendar;
//import tk.sweetvvck.calender.R;
//import tk.sweetvvck.calender.adapter.CalendarGridViewAdapter;
//import tk.sweetvvck.calender.utils.Utils;
import android.app.Activity;
import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.mio.a20180122test.adapter.Calendar_GridView_Adapter;

public class CalendarFragment extends Fragment {
        public static final String ARG_PAGE = "page";
        private int mPageNumber;
        private Calendar mCalendar;
        private Calendar_GridView_Adapter calendarGridViewAdapter;
        public static Fragment create(int pageNumber) {
            CalendarFragment fragment = new CalendarFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_PAGE, pageNumber);
            fragment.setArguments(args);
            return fragment;
        }
        public CalendarFragment() {
        }
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mPageNumber = getArguments().getInt(ARG_PAGE);
            mCalendar = Utils.getSelectCalendar(mPageNumber);
            calendarGridViewAdapter = new Calendar_GridView_Adapter(getActivity(),
                    mCalendar);
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout containing a title and body text.
            ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.calendar_view, container, false);
            GridView titleGridView = (GridView) rootView.findViewById(R.id.gridview);
            TitleGridAdapter titleAdapter = new TitleGridAdapter(getActivity());
            initGridView(titleGridView, titleAdapter);
            GridView calendarView = (GridView) rootView.findViewById(R.id.calendarView);
            initGridView(calendarView, calendarGridViewAdapter);
            //使用initGridView方法需要兩個引數GridView,Calendar_GridView_Adapter
            //使用initGridView可以接著執行setGirdView方法，把findViewById找到的元件再丟入這個方法，可以回傳一個重新打造過的GridView
            //再把這個重造過的GridView，用setAdapter把Calendar_GridView_Adapter裝進去
            //Calendar_GridView_Adapter裡面裝的是new Calendar_GridView_Adapter(getActivity(),mCalendar);
            // 裡面有
            // calStartDate=cal;
//            activity = a;
//            resources=activity.getResources();
//            titles = getDates();
            //的建構式
            //最後回傳一個名為iv的View

            calendarView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    for (int i = 0; i < parent.getCount(); i++) {
                        if ((i % 7) == 6) {
                            parent.getChildAt(i).setBackgroundColor(
                                    getActivity().getResources().getColor(
                                            R.color.text_6));
                        } else if ((i % 7) == 0) {
                            parent.getChildAt(i).setBackgroundColor(
                                    getActivity().getResources().getColor(
                                            R.color.text_7));
                        } else {
                            parent.getChildAt(i).setBackgroundColor(
                                    Color.TRANSPARENT);
                        }
                    }
                    view.setBackgroundColor(getActivity().getResources().getColor(
                            R.color.selection));
                }
            });
            return rootView;
        }
        private void initGridView(GridView gridView, BaseAdapter adapter) {
            gridView = setGirdView(gridView);
            gridView.setAdapter(adapter);// 设置菜单Adapter
        }
        @SuppressWarnings("deprecation")
        private GridView setGirdView(GridView gridView) {
            gridView.setNumColumns(7);// 设置每行列数
            gridView.setGravity(Gravity.CENTER_VERTICAL);// 位置居中
            gridView.setVerticalSpacing(1);// 垂直间隔
            gridView.setHorizontalSpacing(1);// 水平间隔
            gridView.setBackgroundColor(getResources().getColor(R.color.calendar_background));
            WindowManager windowManager = getActivity().getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            int i = display.getWidth() / 7;
            int j = display.getWidth() - (i * 7);
            int x = j / 2;
            gridView.setPadding(x, 0, 0, 0);// 居中
            return gridView;
        }
        public class TitleGridAdapter extends BaseAdapter {
            int[] titles = new int[] {
                    R.string.Sun,
                    R.string.Mon,
                    R.string.Tue,
                    R.string.Wed,
                    R.string.Thu,
                    R.string.Fri,
                    R.string.Sat
            };
            private Activity activity;
            // construct
            public TitleGridAdapter(Activity a) {
                activity = a;
            }
            @Override
            public int getCount() {
                return titles.length;
            }
            @Override
            public Object getItem(int position) {
                return titles[position];
            }
            @Override
            public long getItemId(int position) {
                return position;
            }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LinearLayout iv = new LinearLayout(activity);
                TextView txtDay = new TextView(activity);
                txtDay.setFocusable(false);
                txtDay.setBackgroundColor(Color.TRANSPARENT);
                iv.setOrientation(LinearLayout.VERTICAL);
                txtDay.setGravity(Gravity.CENTER);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                int i = (Integer) getItem(position);
                txtDay.setTextColor(Color.GRAY);
                Resources res = getResources();
                if (i == R.string.Sat) {
                    // 周六
                    txtDay.setBackgroundColor(res.getColor(R.color.title_text_6));
                } else if (i == R.string.Sun) {
                    // 周日
                    txtDay.setBackgroundColor(res.getColor(R.color.title_text_7));
                } else {
                }
                txtDay.setText((Integer) getItem(position));
                iv.addView(txtDay, lp);
                return iv;
            }
        }
    }
}
