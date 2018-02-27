package com.example.mio.a20180122test.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mio.a20180122test.fragment.Calendar_Fragment;

/**
 * Created by mio on 2018/2/26.
 */

public class Calendar_ViewPagerAdapter extends FragmentPagerAdapter {
    public Calendar_ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return Calendar_Fragment.newInstance("GG","1");
    }

    @Override
    public int getCount() {
        return 3;
    }
}
