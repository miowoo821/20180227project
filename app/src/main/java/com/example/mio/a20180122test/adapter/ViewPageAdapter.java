package com.example.mio.a20180122test.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mio.a20180122test.fragment.Activity_Fragment;
import com.example.mio.a20180122test.fragment.Main_Fragment;
import com.example.mio.a20180122test.fragment.Order_Fragment;
import com.example.mio.a20180122test.fragment.Rakuten_Fragment;
import com.example.mio.a20180122test.fragment.Transfer_Fragment;

import java.util.ArrayList;

/**
 * Created by mio on 2018/2/22.
 */

public class ViewPageAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragmentList=new ArrayList<>();
    ArrayList<String>   fragmentTitleList=new ArrayList<>();

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return Transfer_Fragment.newInstance("A","1");
            case 1:
                return Order_Fragment.newInstance("B","2");
            case 2:
                return Main_Fragment.newInstance("C","3");
            case 3:
                return Activity_Fragment.newInstance("D","4");
            case 4:
                return Rakuten_Fragment.newInstance("E","5");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    //寫一個新增fragment的功能
    public void addFragment(Fragment fragment,String title){
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }
}
