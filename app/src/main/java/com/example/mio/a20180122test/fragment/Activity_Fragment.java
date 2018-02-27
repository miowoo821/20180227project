package com.example.mio.a20180122test.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.example.mio.a20180122test.Activities_DAO_DB_Impl;
import com.example.mio.a20180122test.GlobalVariable;
import com.example.mio.a20180122test.R;
import com.example.mio.a20180122test.adapter.ActlistAdapter;
import com.example.mio.a20180122test.adapter.act_order_item_Adapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Activity_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Activity_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View view;
    Context context;
    public static Activities_DAO_DB_Impl dao;//從Interface的賦型改成Activities_DAO_DB_Impl
    //*****************活動列表用的變數************************************
    ArrayList my_Activities_List;
    ListView acticitiesListView;
    ActlistAdapter actAdapter;//給活動列表用的
    //*********************************************************************
    //******************切換帳號用的變數**********************************
    GlobalVariable User;
    String GlobalVariable_User_Account;
    //********************************************************************
    public Activity_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Activity_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Activity_Fragment newInstance(String param1, String param2) {
        Activity_Fragment fragment = new Activity_Fragment();
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
        return inflater.inflate(R.layout.fragment_activity_, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        context=getContext();
        view=getView();

        //***********全域變數******************************************
        User = (GlobalVariable)context.getApplicationContext();//全域變數(資料庫的名字)
        GlobalVariable_User_Account= User.get_GlobalVariable_User_Account();
        //***********全域變數******************************************

        //*************************準備給顯示活動的listview丟資料********************
        dao=new Activities_DAO_DB_Impl(this.getActivity(), GlobalVariable_User_Account);
        my_Activities_List=dao.get_activity_List();
        actAdapter=new ActlistAdapter(this.getActivity(),my_Activities_List,GlobalVariable_User_Account);
        acticitiesListView=(ListView)view.findViewById(R.id.listView2);
        acticitiesListView.setAdapter(actAdapter);
        //***************************************************************************

    }
}
