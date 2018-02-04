package com.example.mio.a20180122test;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.TextView;
import android.widget.Toast;


import com.example.mio.a20180122test.data.Activities;
import com.example.mio.a20180122test.data.Order_Act_Point;
import com.example.mio.a20180122test.data.Orders;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.example.mio.a20180122test.Activity_list.dao;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton imgbtn;
    private int mYear, mMonth, mDay;
    ArrayList my_order_act_list;
    Order_Act_List_Adapter adapter;
    boolean chks[];
    public static Activity_Interface dao;
    String  actd;
    ListView lv;
    ListView lv2;
    ListView lv3;

    //*******************************************
    int feb_days;//2月天數
    int DAY_2000_1_1 = 5;//1999/12/31是星期五
    int day_after_2000_1_1=0;
    Calendar calendar;
    int start_Day_of_the_week;//該月份的第一天是星期幾
    int Days_After_2000_1_1;//從2000/1/1開始之後過了幾天
    int[] MONTH_LENGTH_LIST ={ 31, feb_days, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int year;
    int month;
    int day;

    TextView tv1;
    Button btn1,btn2;

    //*******************************************


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgbtn=(ImageButton) findViewById(R.id.img_orderrecord);
        imgbtn.setOnClickListener(this);
        imgbtn=(ImageButton) findViewById(R.id.img_activityentry);
        imgbtn.setOnClickListener(this);
        imgbtn=(ImageButton) findViewById(R.id.img_switch);
        imgbtn.setOnClickListener(this);
        imgbtn=(ImageButton) findViewById(R.id.img_index);
        imgbtn.setOnClickListener(this);

        dao=new Activities_DAO_DB_Impl(this);
        ArrayList my_act_list=new ArrayList();
        my_act_list=dao.get_activity_List();
        ActlistAdapter adapter1=new ActlistAdapter(MainActivity.this,my_act_list);

        lv=(ListView)findViewById(R.id.listview);
        Log.d("LV到底是甚麼呢：","答案是"+lv);
        lv.setAdapter(adapter1);

        //****************************************************
        calendar=new GregorianCalendar();
        year=calendar.get(Calendar.YEAR) ;
        month=calendar.get(Calendar.MONTH)+1 ;//0~11補正1~12
        day=calendar.get(Calendar.DAY_OF_MONTH) ;


        Log.d("究竟是幾號呢",String.valueOf(day));

//         start_Day_of_the_week=get_Day_of_the_week(year,month,day);//設為輸入的該年月的一號是星期幾
//         Days_After_2000_1_1=get_Days_After_2000_1_1(year,month,day);//設為輸入的年月日是從2000/1/1開始之後過了幾天

        setYearMonthDay();

        tv1=(TextView)findViewById(R.id.textView);
        btn1=(Button)findViewById(R.id.button);
        btn2=(Button)findViewById(R.id.button2);
        tv1.setText(String.valueOf(year+"年"+month+"月"));

//            start_Day_of_the_week=get_Day_of_the_week(year,month,day);//設為輸入的該年月的一號是星期幾
//            Days_After_2000_1_1=get_Days_After_2000_1_1(year,month,day);//設為輸入的年月日是從2000/1/1開始之後過了幾天
        setCalendar(year,month,day);
        //****************************************************


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_orderrecord:
                Intent it=new Intent(MainActivity.this,OrderRecord_page.class);

                startActivity(it);
                break;
            case R.id.img_activityentry:
                Intent it2=new Intent(MainActivity.this,Activity_list.class);

                startActivity(it2);
                break;
            case R.id.img_switch:
                Toast.makeText(MainActivity.this, "Test3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_index:
                Toast.makeText(MainActivity.this, "Test4", Toast.LENGTH_SHORT).show();
                break;
        }
    }
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
                                Log.d("GGGGGGGG1111111",actd);
                                tv1_act.setText(actd);

                                Log.d("GGGGGGGG1111111",tv1_act.getText().toString());

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
                                        Log.d("GGGGGGGG1111111",actd);
                                        tv2_act.setText(actd);
                                        Log.d("GGGGGGGG222222222",tv2_act.getText().toString());
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

                            dao = new Activities_DAO_DB_Impl(MainActivity.this);
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
                    long get_Newest_OrderID = dao.add_order(new Orders(//新增到訂單資料表
                            String.valueOf(tv1.getText().toString()),//tv1=New_order_date
                            Integer.valueOf(ed1.getText().toString()),//ed1=New_order_amount
                            Integer.valueOf(tv2.getText().toString()),//tv2=New_order_N_point
                            String.valueOf(ed2.getText().toString())//ed2=New_order_memo
                    ));
                    Log.d("20180125001", String.valueOf(dao.get_activity_List_filter(Integer.valueOf(tv1.getText().toString())).get(1).Activity_F_Ratio * Integer.valueOf(tv2.getText().toString())));

                    for (int i1 = 0; i1 < chks.length; i1++) {
                        if (chks[i1]) {
                            dao.add_order_act(new Order_Act_Point(//新增到訂單活動資料表
                                    get_Newest_OrderID,
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

        tv1.setText(String.valueOf(year+"年"+month+"月"));
//        start_Day_of_the_week=0;
//        Days_After_2000_1_1=0;

        //setYearMonthDay();
        //setCalendar(year,month,day);
       // checkDatabaseNotes();
        super.onResume();
    }

    public void click_next(View v){
        if(month<12){
            month=month+1;
            onResume();
        }else {
            year=year+1;
            month=1;
            onResume();
        }
        setCalendar(year,month,day);
        onResume();
    }
    public void click_previous(View v){
        if(month==1){
            year=year-1;
            month=12;
            onResume();
        }else {
            month=month-1;
            onResume();
        }
        setCalendar(year,month,day);
    }
    public void setCalendar(int year,int month,int day){
        Log.d( "SetDayLog" , year+"年"+(month)+"月初，共過了"+Days_After_2000_1_1);
        start_Day_of_the_week=get_Day_of_the_week(year,month,day);//設為輸入的該年月的一號是星期幾
        Days_After_2000_1_1=get_Days_After_2000_1_1(year,month,day);//設為輸入的年月日是從2000/1/1開始之後過了幾天
        Log.d( "MyLog" , "開始setCalendar" );
        Log.d( "MyLog" , "月份為"+month+"，年份為"+year );
        //一直加月份的天數，直到指定的月份
//        for(int i=0; i<monthGot-1; ++i)
//        {
//            daysAfter2016_1_1 += MONTH_LENGTH_LIST[i];
//        }
//        Log.d( "MyLog" , "在"+yearGot+"年"+(monthGot-1)+"月底時，過了"+daysAfter2016_1_1+"天");

        // int whatDay = (get_Days_After_2000_1_1(year,month,day) +DAY_2000_1_1 )%7;//求輸入的該年月的一號是星期幾

        int whatDay = start_Day_of_the_week+1;//求輸入的該年月的一號是星期幾
        Log.d( "SetDayLog" , year+"年"+(month)+"月初，是禮拜"+whatDay);
        Log.d( "SetDayLog" , year+"年"+(month)+"月初，共過了"+Days_After_2000_1_1);

        int textDateCount = 1 + whatDay;//星期幾+1=>(甚麼意思?)=>解：因為禮拜整除=0，所以+1把範圍從0~6變成1~7
        int textDateCountLast = whatDay;//設為禮拜幾的前面幾格
//以下有三個步驟，(步驟一跟二對調)設定當月日期、上個月日期(最後幾天)、下個月日期(最初幾天)

        //步驟一，設定當月的日期(步驟一跟二對調)
        for(int i=1; i<=MONTH_LENGTH_LIST[month-1]; ++i)//抓出陣列裡面對應的月份(從0開始所以要-1)，接著從1號開始迴圈到該月份的最後一天
        {



            String foo = "day_" + textDateCount+"_0";//設定一個字串(起始日的星期幾)=DateText+星期幾
            Log.d( "MyLog" , "使用ID為"+foo);

//            //------------測試用，抓note欄
//            for(int i2=1;i2<4;i2++){
//                String foo2="day_" + textDateCount+"_"+i2;
//
//                int resID2 = getResources().getIdentifier(foo2 , "id" , getPackageName());
//                TextView someDateText2 = (TextView) findViewById(resID2);//找到TextView
//
//                Log.d( "MyLog" , "日期為"+year+"/"+month+"/"+i+"_"+i2);
//                //someDateText2.setVisibility(View.VISIBLE);
//                someDateText2.setBackgroundColor(0xffffffff);
//                someDateText2.setTextColor(0xff000000);
//                someDateText2.setText(Integer.toString(i2));//設定TextView
//            }
//            //------------測試用，抓note欄

            int resID = getResources().getIdentifier(foo , "id" , getPackageName());

            TextView someDateText = (TextView) findViewById(resID);//找到TextView

            someDateText.setText(Integer.toString(i));//設定TextView

            someDateText.setBackgroundColor(0xffffffff);
            someDateText.setTextColor(0xff000000);
            someDateText.setTextSize(8);
            if(i==day){
                someDateText.setTextColor(0xffffffff);
                someDateText.setTextSize(10);
                //--------粗體要加這兩行-------
                TextPaint tp = someDateText.getPaint();//
                tp.setFakeBoldText(true);
                //--------粗體要加這兩行-------
                someDateText.setBackgroundColor(0xffff0000);

                someDateText.clearComposingText();
            }
            textDateCount++;//往下一個DAY

        }

        //設定上個月的日期，1月會有問題
        //步驟二，設定上個最後月幾天的日期(步驟一跟二對調)
        // 設定一月的上個最後月幾天的日期時，切換到去年12月的時候會發生問題，所以要把1月的情況特地挑出來寫
        // 會發生的問題是....?
        if(month==1)//如果輸入的月份是1月
        {
            for (int i = MONTH_LENGTH_LIST[11]; 1 > 0; i--)//令i等於12月的天數，然後無窮迴圈到執行break
            {

                if (textDateCountLast > 0)//迴圈跑出有幾個上個月的天數(透過本月1號是星期幾來找出)。不能大於本月的起始星期
                {
                    String foo = "day_" + textDateCountLast+"_0";
                    //      Log.d("MyLog", "使用ID為" + foo);
                    int resID = getResources().getIdentifier(foo, "id", getPackageName());
                    //      Log.d("MyLog", "使用ID為" + resID);
                    TextView someDateText = (TextView) findViewById(resID);
                    someDateText.setBackgroundColor(0xffffffff);
                    someDateText.setTextColor(0xFF8B8B8B);

                    TextView tv1;
                    // someDateText.setText(Integer.toString(i));//日期設定為12月的第i天(31、30、29、28、27...

                    //       Log.d("MyLog", "使用TextView為" +someDateText);
                    //      Log.d("MyLog", "使用I為" +i);
                    someDateText.setText(String.valueOf(31));//日期設定為12月的第i天(31、30、29、28、27...

                    textDateCountLast--;//每執行一次就-1，最後一個執行的是本星期的起始點
                }
                else
                {
                    break;
                }
            }
        }
        else
        {
            for (int i = MONTH_LENGTH_LIST[month - 2]; 1 > 0; i--)//如果輸入的月份不是1月，則跑一個上個月的迴圈(二月跑一月，三月跑二月)
            {
                if (textDateCountLast > 0)
                {
                    String foo = "day_" + textDateCountLast+"_0";
                    //       Log.d("MyLog", "使用ID為" + foo);
                    int resID = getResources().getIdentifier(foo, "id", getPackageName());
                    TextView someDateText = (TextView) findViewById(resID);
                    someDateText.setText(Integer.toString(i));
                    someDateText.setBackgroundColor(0xffffffff);
                    someDateText.setTextColor(0xFF8B8B8B);
                    textDateCountLast--;
                }
                else
                {
                    break;
                }
            }
        }
//步驟三：設定下個月日期(最初幾天)
        //(刪)設定下個月的日期，12月會有問題
        //(刪)int i = whatDay + MONTH_LENGTH_LIST[monthGot-1]+1;
        //(刪)Log.d( "MyLog" , "下個月的開始dateBlock編號為"+i);
        // (刪)在12月設定下個月的日期時會有問題，所以要把12月的情況特地挑出來寫
        //(刪) 會發生的問題是....?
        int newMonthDate = 1;
        for(int i = whatDay + MONTH_LENGTH_LIST[month-1]+1; i<=42; ++i){
            // MONTH_LENGTH_LIST[monthGot-1]這個是陣列裡面第X個月，由於陣列從0開始，所以要求自己的月份要-1
            // /本月起始日的星期+本月天數，最後再+1=42格裡面剩下幾個是下個月初的格子
            //i的起始值為本月月初是星期幾，加上本月天數可算出本月結束是星期幾，

            String foo = "day_" + i+"_0";
            //     Log.d( "MyLog" , "步驟三使用ID為"+foo);
            int resID = getResources().getIdentifier(foo , "id" , getPackageName());
            TextView someDateText = (TextView) findViewById(resID);
            someDateText.setText(Integer.toString(newMonthDate));
            someDateText.setBackgroundColor(0xffffffff);
            someDateText.setTextColor(0xFF8B8B8B);
            newMonthDate++;
        }
    }
    public int get_Day_of_the_week(int year,int month,int day){//求某日期是星期幾

        DAY_2000_1_1=5;
        for (int i = 2000; i < year ; i++) {//迴圈次數等於參數的年距離2000年有幾年，進而求出要加幾天
            if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {//判斷這一年要不要潤
                DAY_2000_1_1 = (DAY_2000_1_1 + 366) % 7;//潤的話這年就是+366天，接著取7餘數判斷出是星期幾
                Log.d("GFDHDFGHGJGH","GFDHDFGHGJGH==============="+String.valueOf(DAY_2000_1_1));
            } else {
                DAY_2000_1_1 = (DAY_2000_1_1 + 365) % 7;////不潤的話這年就是+365天，接著取7餘數判斷出是星期幾
                Log.d("GFDHDFGHGJGH","GFDHDFGHGJGH==============="+String.valueOf(DAY_2000_1_1));
            }
            //每次回圈決定下一年的  是星期幾
        }

        for(int i=0; i<month-1; i++){
            DAY_2000_1_1 += MONTH_LENGTH_LIST[i];
        }

        int Day_of_the_week =(DAY_2000_1_1)%7;
//        if((month-1)==0)
//        {
//            Log.d( "SetDayLog" , "在"+(year-1)+"年"+(12)+"月底時，過了"+DAY_2000_1_1+"天");
//        }
//        else
//        {
//            Log.d("SetDayLog", "在" + year + "年" + (month - 1) + "月底時，過了" + DAY_2000_1_1 + "天");
//        }
        return Day_of_the_week;
    }
    public int get_Days_After_2000_1_1(int year,int month,int day){//求某日期是從2000/1/1號後過了幾天

        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {//求那年的二月有幾天
            feb_days = 29;

        } else {
            feb_days = 28;

        }
        day_after_2000_1_1=0;

        for(int i = 2000; i < year; i++)//得到年分
        {
            if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                //      Log.d("TEST,","day_after_2000_1_1========================"+day_after_2000_1_1);
                day_after_2000_1_1 += 366;

            } else {
                day_after_2000_1_1 += 365;
            }
        }
        for(int i=0; i<month-1; i++){//得到月份的開頭
            day_after_2000_1_1 += MONTH_LENGTH_LIST[i];
        }
//        if((month)==1)
//        {
//            Log.d( "SetDayLog" , "在"+(year-1)+"年"+(12)+"月底時，過了"+day_after_2000_1_1+"天");
//        }
//        else
//        {
//            Log.d("SetDayLog", "在" + year + "年" + (month) + "月底時，過了" + day_after_2000_1_1 + "天");
//        }
//        day_after_2000_1_1= day_after_2000_1_1 % 365;
//        day_after_2000_1_1=day_after_2000_1_1 /7;
        return day_after_2000_1_1;
    }
    public void setYearMonthDay(){
        if( (year%400==0) || ( year%4==0 && year%100!=0 ) )
        {
            Log.d( "MyLog" , "Leap Year" );
            MONTH_LENGTH_LIST[1] = 29;

        }
        else
        {
            Log.d( "MyLog" , "Average Year" );
            MONTH_LENGTH_LIST[1] = 28;

        }
    }


    public void checkDatabaseNotes(){
        //先run過所有日期
        for(int i=1; i<=42; ++i) {
//----------設定一個字串變數內容為DateText+i(要拿來找ID用的)，名字叫做dateTextViewID
            String dateTextViewID = "day_" + Integer.toString(i)+"_0";
//----------設一個int變數，內容為R.id.剛剛那個變數的id(用來findviewbyid物件)
            int resID = getResources().getIdentifier(dateTextViewID, "id", getPackageName());
//----------用來findviewbyid物件
            TextView someDateText = (TextView) findViewById(resID);
//----------設一個字串變數把someDateText裡面的字串抓出來放進去dateTextViewText(找日期)
            String dateTextViewText = someDateText.getText().toString();
            if(Integer.valueOf(dateTextViewText)<10){
                dateTextViewText=0+dateTextViewText;
            }
            Log.d("dateTextViewText", "dateTextViewText===============================" + dateTextViewText);
//-----------設一個字串變數month
            String month1;
//------------↓在前七次迴圈，且日期大於20時(前面第一排有可能有上個月的東西)

            if (i <= 7 && Integer.parseInt(dateTextViewText) >= 20) {
                if(month!=1){
                    month1 = Integer.toString(month - 1);//
                }else {
                    month1 = "12";
                }
            } else if (i >= 25 && Integer.parseInt(dateTextViewText) < 15)//下個月的東西
            {
                if(month!=12) {
                    month1 = Integer.toString(month + 1);
                }else {
                    month1="1";
                }

            } else {
                month1 = Integer.toString(month);
            }
            if(Integer.valueOf(month1)<10){
                String.valueOf(month1);
                month1=0+month1;
            }

            Cursor noteday=dao.get_activity_date(Integer.valueOf(String.valueOf(year)+String.valueOf(month1)+String.valueOf(dateTextViewText)));
            Log.d("dateTextViewText", "dateTextViewText===============================" + Integer.valueOf(String.valueOf(year)+String.valueOf(month1)+String.valueOf(dateTextViewText)));
            Log.d("FHHGHGHFGHHG","DFFFFDD=============="+noteday);
            if(noteday!=null){

                int noteAmount = noteday.getCount();
                int bar;
                if(noteAmount>=3)//假設超過3條note，也只有3條
                {       bar = 3;                }
                else
                {       bar = noteAmount;       }

                for(int k=1; k<=bar; ++k)//接下來開始跑note，最多只跑3條
                {
                    String useID = "day_"+Integer.toString(i)+"_"+Integer.toString(k);
                    int rID = getResources().getIdentifier(useID , "id" , getPackageName());
                    TextView noteText = (TextView) findViewById(rID);
                    noteText.setVisibility(View.VISIBLE);
                    //把noteText的view找起來,要開始迴圈填資料了

                    noteday.moveToNext();//剛剛回傳的Cursor物件(資料表)往下一筆移動，
                    Log.d("CalendarLog","得到一個Note : "+noteday.getString(1));
                    String rawString = noteday.getString(1);//用一個叫做rawString的字串變數抓這一筆的第2個欄位填進去
                    //判斷這個字串長度是不是大於四
                    if(rawString.length()>10)
                    {
                        rawString = rawString.substring(0, 10);//假設字串長度大於4就只抓從頭開始到第四個字
                    }
                    noteText.setText(rawString);//最後填入剛剛findviewbyid的noteText
                }
            }
        }
    }
}
