package com.example.mio.a20180122test.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.mio.a20180122test.fragment.Calendar_Fragment;

/**
 * Created by mio on 2018/2/26.
 */

public class Calendar_ViewPagerAdapter extends FragmentPagerAdapter {
    int year;
    int month;
    int day;
    public Calendar_ViewPagerAdapter(FragmentManager fm,int year,int month,int day) {
        super(fm);
        this.day=day;
        this.month=month;
        this.year=year;



    }


    @Override
    public Fragment getItem(int position) {

        return Calendar_Fragment.newInstance("GG","1",year,month,day);

    }

    @Override
    public int getCount() {
        return 200;
    }


}
