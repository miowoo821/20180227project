package com.example.mio.a20180122test.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mio.a20180122test.Activities_DAO_DB_Impl;
import com.example.mio.a20180122test.GlobalVariable;
import com.example.mio.a20180122test.R;
import com.example.mio.a20180122test.adapter.ActlistAdapter;
import com.example.mio.a20180122test.adapter.Calendar_ViewPagerAdapter;
import com.example.mio.a20180122test.adapter.Order_Act_List_Adapter;
import com.example.mio.a20180122test.adapter.ViewPageAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Main_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Main_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Main_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Context context;
    View view;
    public static Activities_DAO_DB_Impl dao;//從Interface的賦型改成Activities_DAO_DB_Impl
    //*********活動列表用的變數****************************
    ArrayList my_Act_List;
    ActlistAdapter actAdapter;
    ListView actListView;
    //*****************************************************


    //******************切換帳號用的變數*******************
    GlobalVariable User;
    String GlobalVariable_User_Account;
    //*****************************************************


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Main_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Main_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Main_Fragment newInstance(String param1, String param2) {
        Main_Fragment fragment = new Main_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        context=getContext();
        view=getView();

        //***********全域變數*****************
        User = (GlobalVariable)context.getApplicationContext();//全域變數(資料庫的名字)
        GlobalVariable_User_Account= User.get_GlobalVariable_User_Account();
        //***********全域變數*****************

        //*************************準備給顯示活動的listview丟資料********************
        dao=new Activities_DAO_DB_Impl(this.getActivity(),GlobalVariable_User_Account);//this.getActivity()可得到此fragment所依附的Activity，可不寫this
        my_Act_List=dao.get_activity_List();
        actAdapter=new ActlistAdapter(this.getActivity(),my_Act_List,GlobalVariable_User_Account);
        actListView=(ListView)view.findViewById(R.id.listview);
        actListView.setAdapter(actAdapter);
        //*****************************************************************



        //************************開始寫可以滑動的日曆fragment*****************************
        ViewPager calender_viewpager;
        Calendar_ViewPagerAdapter my_CalenfarViewPageAdapter;

        my_CalenfarViewPageAdapter=new Calendar_ViewPagerAdapter(getChildFragmentManager());
        calender_viewpager=(ViewPager)view.findViewById(R.id.calender_viewpager);
        calender_viewpager.setAdapter(my_CalenfarViewPageAdapter);
        calender_viewpager.setCurrentItem(1);
        //************************以上是寫可以滑動的日曆fragment*****************************


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
