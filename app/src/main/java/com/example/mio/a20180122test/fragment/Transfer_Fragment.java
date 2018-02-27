package com.example.mio.a20180122test.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mio.a20180122test.GlobalVariable;
import com.example.mio.a20180122test.R;
import com.example.mio.a20180122test.Transfer_Activity;
import com.example.mio.a20180122test.adapter.account_list_item_Adapter;
import com.example.mio.a20180122test.data.Account_DAO_DB;
import com.example.mio.a20180122test.detail.account_detail;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Transfer_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Transfer_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View view;//fragment由於不是繼承AppCompatActivity的關係(已經繼承fragment，所以沒有activity的一切方法(activity是繼承了AppCompatActivity)，
    // 因此無法直接findviewbyid所以要透過view
    Context context;//理由同上，fragment沒有繼承activity
    //******************************************************
    ListView account_list_lv;

    Account_DAO_DB ADAO;
    ArrayList my_account_list;
    account_list_item_Adapter adapter;

    GlobalVariable User;
    String GlobalVariable_User_Account;
    //******************************************************



    public Transfer_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Transfer_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Transfer_Fragment newInstance(String param1, String param2) {
        Transfer_Fragment fragment = new Transfer_Fragment();
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
        return inflater.inflate(R.layout.fragment_transfer_, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        view=getView();//由於生命週期的關係，在onCreat後的onCreateView，視圖元件才被建立，所以要放在onCreat後的週期裡
        context=getContext();
        //***********全域變數*****************這兩行貌似可以放在onCreat裡面，因為他好像不需要視圖元件(View)
        User = (GlobalVariable) context.getApplicationContext();//全域變數(資料庫的名字)
        GlobalVariable_User_Account= User.get_GlobalVariable_User_Account();
        //***********全域變數*****************

        //*******************************抓出帳戶列表********************************
        ADAO=new Account_DAO_DB(this.getActivity(),GlobalVariable_User_Account);//this.getActivity()可得到此fragment所依附的Activity，可不寫this
        my_account_list=new ArrayList();
        my_account_list=ADAO.get_Account_list();
        adapter=new account_list_item_Adapter(this.getActivity(),my_account_list,GlobalVariable_User_Account);//this.getActivity()可得到此fragment所依附的Activity，可不寫this


        account_list_lv=(ListView)view.findViewById(R.id.account_list_lv);//找到該元件
        account_list_lv.setAdapter(adapter);
        //**************************************************************************

        //*******************************帳號列表的事件(長按短按)*******************
        account_list_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(view.getContext(),account_detail.class);

                /*
1.MainActivity.this：表示MainActivity对象，一般用在内部类中指示外面的this，如果在内部类直接用this，指示的是内部类本身。因为MainActivity继承Activity，而Activity继承Context，所以它也可以用来提供Activity Contex；
2.this：表示当前对象；一般而言，在哪个类中调用，就是指向该对象。
3.getContext()：这个是View类中提供的方法，在继承了View的类中才可以调用，返回的是当前View运行在哪个Activity Context中。
4.getActivity和getcontext其实差不多，一般在fragment中使用的时候，用此方法获取。
4.在定义适配器等工具类的时候，很多时候都是要把从头当做方法参数传进来，在构造方法中定义。
                 */
                //adapter=new ActlistAdapter(Activity_list.this,dao.get_activity_List());
                it.putExtra("position",ADAO.get_Account_list().get(i).ID);
                //it.putExtra("position",i+1);//直接給數字無法抓到準確的id，因為有時候id不等於位置(順序)
                startActivity(it);
            }
        });
        account_list_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()  {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String Change_DB_Name=ADAO.get_Account_list().get(i).Account_Name;
                //**********痊癒變數******************
                GlobalVariable User = (GlobalVariable)context.getApplicationContext();
                User.set_GlobalVariable_User_Account(Change_DB_Name);
                //***********全域變數*****************
                Toast.makeText(view.getContext(),"當前帳號修改為"+Change_DB_Name,Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        //**************************************************************************
    }
}
