package com.example.mio.a20180122test;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mio.a20180122test.adapter.ActlistAdapter;
import com.example.mio.a20180122test.adapter.Order_Act_List_Adapter;
import com.example.mio.a20180122test.adapter.ViewPageAdapter;
import com.example.mio.a20180122test.data.Account_DAO_DB;
import com.example.mio.a20180122test.data.Activities;
import com.example.mio.a20180122test.data.Order_Act_Point;
import com.example.mio.a20180122test.data.Orders;
import com.example.mio.a20180122test.fragment.Activity_Fragment;
import com.example.mio.a20180122test.fragment.Main_Fragment;
import com.example.mio.a20180122test.fragment.Order_Fragment;
import com.example.mio.a20180122test.fragment.Rakuten_Fragment;
import com.example.mio.a20180122test.fragment.Transfer_Fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity   {
    ImageButton imgbtn;
    private int mYear, mMonth, mDay;
    ArrayList my_order_act_list;
    Order_Act_List_Adapter adapter;
    boolean chks[];
    public  Activities_DAO_DB_Impl dao;//從Interface的賦型改成Activities_DAO_DB_Impl
    String  actd;

    ListView lv2;
    ListView lv3;

    //*******************************************

    TextView tv1;
    Button btn1,btn2;
    //*******************************************
    public static Account_DAO_DB Name_DAO;
    //String DatabaseName;
    GlobalVariable User;
    String GlobalVariable_User_Account;
    //**********為了抓使用帳戶的ID*********************************
//    Account_DAO_DB ADAO;(不想用了，ID不好找)
    //**********為了抓使用帳戶的ID*********************************


    //************************開始寫可以滑動的fragment*****************************
    ViewPager main_ViewPager;
    ViewPageAdapter my_ViewPageAdapter;
    TabLayout main_TabLayout;
    //************************以上是寫可以滑動的fragment*****************************


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //***********全域變數*****************
        User = (GlobalVariable) getApplicationContext();//全域變數(資料庫的名字)
        GlobalVariable_User_Account= User.get_GlobalVariable_User_Account();
        //***********全域變數*****************

        //**********為了抓使用帳戶的ID*********************************
//        ADAO=new Account_DAO_DB(MainActivity.this,GlobalVariable_User_Account);(不想用了，ID不好找)
        //**********為了抓使用帳戶的ID*********************************

        Log.d("TESTTT","全域變數============="+User.get_GlobalVariable_User_Account());
//        Name_DAO=new Account_DAO_DB(this);
//        DatabaseName=Name_DAO.get_account("DEFAULT");//DEFAULT之後再來修改
        dao=new Activities_DAO_DB_Impl(this,GlobalVariable_User_Account);
        ArrayList my_act_list=new ArrayList();
        my_act_list=dao.get_activity_List();
        ActlistAdapter adapter1=new ActlistAdapter(MainActivity.this,my_act_list,GlobalVariable_User_Account);




        //****************************************************


        //****************************************************


        //************************以下是寫可以滑動的fragment*****************************
        my_ViewPageAdapter=new ViewPageAdapter(getSupportFragmentManager());//先new出來
        main_TabLayout=(TabLayout)findViewById(R.id.main_tablayout);
        main_ViewPager=(ViewPager)findViewById(R.id.main_viewpager);

        main_ViewPager.setAdapter(my_ViewPageAdapter);

        //以下兩行可以連動tab與viewpager
        main_ViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(main_TabLayout));//監聽page改變
        main_TabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(main_ViewPager));//監聽Tab改變

        setup_viewpager(main_ViewPager);
        main_ViewPager.setCurrentItem(2);
        //************************以上是寫可以滑動的fragment*****************************


    }

    //************************以下是為了寫可以滑動的fragment所準備的方法*****************************
    public void setup_viewpager(ViewPager viewPager){
        my_ViewPageAdapter=new ViewPageAdapter(getSupportFragmentManager());

        my_ViewPageAdapter.addFragment(new Transfer_Fragment(),"change");
        my_ViewPageAdapter.addFragment(new Order_Fragment(),"order");
        my_ViewPageAdapter.addFragment(new Main_Fragment(),"Main");
        my_ViewPageAdapter.addFragment(new Activity_Fragment(),"Activity");
        my_ViewPageAdapter.addFragment(new Rakuten_Fragment(),"web");

        viewPager.setAdapter(my_ViewPageAdapter);
    }
    //************************以上是為了寫可以滑動的fragment所準備的方法*****************************

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.newconsumption,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_add_activity:
                AlertDialog.Builder builder_act=new AlertDialog.Builder(MainActivity.this);
                builder_act.setTitle("登錄活動");
                LayoutInflater inflater_act=LayoutInflater.from(MainActivity.this);
                final View newactivity=inflater_act.inflate(R.layout.newactivity,null);
                lv2=(ListView)findViewById(R.id.listView2);//這邊只有public boolean onOptionsItemSelected(MenuItem item) {裡面的人能用
                final TextView tv1_act=newactivity.findViewById(R.id.act_start_date);
                final TextView tv2_act=newactivity.findViewById(R.id.act_end_date);
                final TextView tv3=newactivity.findViewById(R.id.feedback_start_date);
                final TextView tv4=newactivity.findViewById(R.id.feedback_end_date);

                final EditText act_name=newactivity.findViewById(R.id.act_name);
                final EditText limted=newactivity.findViewById(R.id.limted);
                final  TextView act_S_D=newactivity.findViewById(R.id.act_start_date);
                final  TextView act_E_D=newactivity.findViewById(R.id.act_end_date);
                final  TextView F_S_D=newactivity.findViewById(R.id.feedback_start_date);
                final  TextView F_E_D=newactivity.findViewById(R.id.feedback_end_date);
                final EditText ratio=newactivity.findViewById(R.id.ratio);
                final EditText memo=newactivity.findViewById(R.id.memo);

                tv1_act.setOnClickListener(new View.OnClickListener() {
                    //int mYear, mMonth, mDay;
                    int mYear, mMonth, mDay;
                    Calendar c;
                    @Override
                    public void onClick(View view) {
                        c=Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);
                        //DatePickerDialog連續執行兩次，就有兩層DatePickerDialog，最上面那層就是比較後面執行的，所以先填的是後面的日期
                        //修正方法，在第一個DatePickerDialog關閉才跳出第二個
                        new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                                String s1= String.valueOf(i);
                                String s2="";
                                String s3="";
                                if(i1<10){
                                    s2= "0"+String.valueOf(i1);
                                }else {
                                    s2= String.valueOf(i1);
                                }

                                if(i2<10){
                                    s3= "0"+String.valueOf(i2);
                                }else {
                                    s3=String.valueOf(i2);
                                }
                                actd = s1+s2+s3;
                                Log.d("GG111",actd);
                                tv1_act.setText(actd);

                                Log.d("GG111",tv1_act.getText().toString());

                                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                        i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                                        String s1= String.valueOf(i);
                                        String s2="";
                                        String s3="";
                                        if(i1<10){
                                            s2= "0"+String.valueOf(i1);
                                        }else {
                                            s2= String.valueOf(i1);
                                        }

                                        if(i2<10){
                                            s3= "0"+String.valueOf(i2);
                                        }else {
                                            s3=String.valueOf(i2);
                                        }
                                        actd = s1+s2+s3;
                                        Log.d("GGG111",actd);
                                        tv2_act.setText(actd);
                                        Log.d("GGGG222",tv2_act.getText().toString());
                                    }
                                },mYear, mMonth, mDay).show();
                            }
                        },mYear, mMonth, mDay).show();
                    }
                });
                tv2_act.setOnClickListener(new View.OnClickListener() {
                    int mYear, mMonth, mDay;
                    @Override
                    public void onClick(View view) {

                        Calendar c=Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);
                        new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                                String s1 = (String.valueOf(i));
                                String s2="";
                                String s3="";
                                if(i1<10){
                                    s2= "0"+String.valueOf(i1);
                                }else {
                                    s2= String.valueOf(i1);
                                }

                                if(i2<10){
                                    s3= "0"+String.valueOf(i2);
                                }else {
                                    s3=String.valueOf(i2);
                                }
                                actd = s1+s2+s3;

                                tv1_act.setText(actd);
                                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                        i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                                        String s1 = (String.valueOf(i));
                                        String s2="";
                                        String s3="";
                                        if(i1<10){
                                            s2= "0"+String.valueOf(i1);
                                        }else {
                                            s2= String.valueOf(i1);
                                        }

                                        if(i2<10){
                                            s3= "0"+String.valueOf(i2);
                                        }else {
                                            s3=String.valueOf(i2);
                                        }
                                        actd = s1+s2+s3;
                                        tv2_act.setText(actd);
                                    }
                                },mYear, mMonth, mDay).show();
                            }
                        },mYear, mMonth, mDay).show();
////                int mYear, mMonth, mDay;
////                Calendar c=Calendar.getInstance();
//                mYear = c.get(Calendar.YEAR);
//                mMonth = c.get(Calendar.MONTH);
//                mDay = c.get(Calendar.DAY_OF_MONTH);
//                new DatePickerDialog(ActivityPage.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                        i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
//                        String str = (String.valueOf(i+"/"+i1+"/"+i2));
//
//                        tv2.setText(str);
//                    }
//                },mYear, mMonth, mDay).show();
                    }
                });
                tv3.setOnClickListener(new View.OnClickListener() {
                    int mYear, mMonth, mDay;
                    @Override
                    public void onClick(View view) {

                        Calendar c=Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);
                        //日曆主題不同new DatePickerDialog(MainActivity.this,android.R.style.Theme_Holo_Dialog, new DatePickerDialog.OnDateSetListener() {
                        new DatePickerDialog(MainActivity.this,android.R.style.Theme_Holo_Dialog, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                                String s1 = (String.valueOf(i));
                                String s2="";
                                String s3="";
                                if(i1<10){
                                    s2= "0"+String.valueOf(i1);
                                }else {
                                    s2= String.valueOf(i1);
                                }

                                if(i2<10){
                                    s3= "0"+String.valueOf(i2);
                                }else {
                                    s3=String.valueOf(i2);
                                }
                                actd = s1+s2+s3;


                                tv3.setText(actd);
                                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                        i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                                        String s1 = (String.valueOf(i));
                                        String s2="";
                                        String s3="";
                                        if(i1<10){
                                            s2= "0"+String.valueOf(i1);
                                        }else {
                                            s2= String.valueOf(i1);
                                        }

                                        if(i2<10){
                                            s3= "0"+String.valueOf(i2);
                                        }else {
                                            s3=String.valueOf(i2);
                                        }
                                        actd = s1+s2+s3;


                                        tv4.setText(actd);
                                    }
                                },i, i1-1, i2+13).show();
                            }
                        },mYear, mMonth, mDay).show();
                    }
                });
                tv4.setOnClickListener(new View.OnClickListener() {
                    int mYear, mMonth, mDay;
                    @Override
                    public void onClick(View view) {

                        Calendar c=Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);
                        new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                                String s1 = (String.valueOf(i));
                                String s2="";
                                String s3="";
                                if(i1<10){
                                    s2= "0"+String.valueOf(i1);
                                }else {
                                    s2= String.valueOf(i1);
                                }

                                if(i2<10){
                                    s3= "0"+String.valueOf(i2);
                                }else {
                                    s3=String.valueOf(i2);
                                }
                                actd = s1+s2+s3;


                                tv3.setText(actd);

                                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                        i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                                        String s1 = (String.valueOf(i));
                                        String s2="";
                                        String s3="";
                                        if(i1<10){
                                            s2= "0"+String.valueOf(i1);
                                        }else {
                                            s2= String.valueOf(i1);
                                        }

                                        if(i2<10){
                                            s3= "0"+String.valueOf(i2);
                                        }else {
                                            s3=String.valueOf(i2);
                                        }
                                        actd = s1+s2+s3;


                                        tv4.setText(actd);
                                    }
                                },i, i1-1, i2+13).show();
                            }
                        },mYear, mMonth, mDay).show();
                    }
                });

                builder_act.setPositiveButton("確定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {//按下確定後執行新增資料的行為

                        dao.add(new Activities(
                                        act_name.getText().toString(),
                                        Integer.valueOf( act_S_D.getText().toString()),
                                        Integer.valueOf(act_E_D.getText().toString()),
                                        F_S_D.getText().toString(),
                                        F_E_D.getText().toString(),
                                        Integer.valueOf(limted.getText().toString()),
                                        Integer.valueOf( ratio.getText().toString()),
                                        memo.getText().toString()
                                )
                        );
                        Toast.makeText(MainActivity.this,"新增成功",Toast.LENGTH_SHORT).show();
                        onResume();
                    }

                });

                builder_act.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }

                });

                builder_act.setView(newactivity);
                builder_act.show();
                break;
            case R.id.menu_add_order:
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);//新增一個會在MainActivity裡面彈出的Dialog物件
            builder.setTitle("新增紀錄");

            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);//layout解壓縮用的，可以把res裡面的layout挖出來
            final View neworder = inflater.inflate(R.layout.neworder, null);//第一個參數是要放入的Layout,第二個放null(為什麼)
            final TextView tv1 = neworder.findViewById(R.id.New_order_date);
            final TextView tv2 = neworder.findViewById(R.id.New_order_N_point);
            final EditText ed1 = neworder.findViewById(R.id.New_order_amount);
            final EditText ed2 = neworder.findViewById(R.id.New_order_memo);

            tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Calendar c = Calendar.getInstance();//getInstance()是抓現在的時間
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);
                    new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                        //                    DatePickerDialog 是一個 Android 寫好的類別,
//                    它可以提供使用者簡單操作的設定日期介面,
//                    呼叫它的方式就是直接 new DatePickerDialog 並且傳入對應的參數
//
//                    第一個參數是 Context , 也就是說必須把 MainActivity 本身或者 Context 物件傳入。
//                    第二個參數是 OnDateSetListener , 這邊是實作 OnDateSetListener 這個介面的事件, 它提供使用者操控完日期介面後, 所傳回的日期。
//                    第三個是西元幾年, 輸入多少就會得到幾年，我們可以透過 Canlendar 的幫忙得到這個資訊。
//                    第四個是幾月,輸入多少就會得到幾月， 我們可以透過 Canlendar 的幫忙得到這個資訊。
//                    第五個是幾號, 輸入多少就會得到幾號，我們可以透過 Canlendar 的幫忙得到這個資訊。
//                    第三到五個參數決定一打開DatePickerDialog時會出現的日期
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {//i1   i2  i3是回傳年月日
                            i1 = i1 + 1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                            String s1 = String.valueOf(i);
                            String s2 = "";
                            String s3 = "";
                            if (i1 < 10) {
                                s2 = "0" + String.valueOf(i1);
                            } else {
                                s2 = String.valueOf(i1);
                            }
                            if (i2 < 10) {
                                s3 = "0" + String.valueOf(i2);
                            } else {
                                s3 = String.valueOf(i2);
                            }
                            String str = s1 + s2 + s3;
                            tv1.setText(str);

                            dao = new Activities_DAO_DB_Impl(MainActivity.this,GlobalVariable_User_Account);
                            my_order_act_list = new ArrayList();
                            my_order_act_list = dao.get_activity_List_filter(Integer.valueOf(tv1.getText().toString()));

                            chks = new boolean[my_order_act_list.size()];
                            adapter = new Order_Act_List_Adapter(MainActivity.this, my_order_act_list, chks);

                            lv3 = neworder.findViewById(R.id.listView3);
                            lv3.setAdapter(adapter);
                        }
                    }, mYear, mMonth, mDay).show();
                }
            });

            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    tv2.setText(String.valueOf(Integer.valueOf(ed1.getText().toString()) / 100));

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            };
            ed1.addTextChangedListener(textWatcher);

            builder.setView(neworder);
            builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    long get_Newest_OrderID = dao.add_order(new Orders(//新增到訂單資料表==========1
                            String.valueOf(GlobalVariable_User_Account),//tv2=New_order_N_point
                            String.valueOf(tv1.getText().toString()),//tv1=New_order_date
                            Integer.valueOf(ed1.getText().toString()),//ed1=New_order_amount
                            String.valueOf(ed2.getText().toString())//ed2=New_order_memo
                    ));
//                    Log.d("20180125001", String.valueOf(dao.get_activity_List_filter(Integer.valueOf(tv1.getText().toString())).get(1).Activity_F_Ratio * Integer.valueOf(tv2.getText().toString())));

                    for (int i1 = 0; i1 < chks.length; i1++) {
                        if (chks[i1]) {
                            dao.add_order_act(new Order_Act_Point(//新增到訂單活動資料表==============2
                                    get_Newest_OrderID,
                                    GlobalVariable_User_Account,
                                    dao.get_activity_List_filter(Integer.valueOf(tv1.getText().toString())).get(i1)._id,
                                    dao.get_activity_List_filter(Integer.valueOf(tv1.getText().toString())).get(i1).Activity_Name,
                                    dao.get_activity_List_filter(Integer.valueOf(tv1.getText().toString())).get(i1).Activity_F_Ratio * Integer.valueOf(tv2.getText().toString())

                            ));
                            Log.d("20180125001", String.valueOf(dao.get_activity_List_filter(Integer.valueOf(tv1.getText().toString())).get(i1).Activity_F_Ratio * Integer.valueOf(tv2.getText().toString())));

                        }
                    }
//                StringBuilder sb = new StringBuilder();
//                for (int i1=0;i<chks.length;i1++)
//                {
//                    if (chks[i1])
//                    {
//
//                    }
//                }
                    Log.d("test20180125", String.valueOf(chks.length));
                    Log.d("test20180125", String.valueOf(chks.length));
                    Log.d("test20180125", String.valueOf(my_order_act_list.size()));
                }
            });

            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            builder.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {

        refresh_data();

        super.onResume();
    }
public void refresh_data(){
    ArrayList my_act_list=new ArrayList();
    my_act_list=dao.get_activity_List();
    ActlistAdapter adapter1=new ActlistAdapter(MainActivity.this,my_act_list,User.get_GlobalVariable_User_Account());

    adapter1.notifyDataSetChanged();
    Log.d("使用者帳號","GlobalVariable_User_Account==============="+GlobalVariable_User_Account);
    }

}

