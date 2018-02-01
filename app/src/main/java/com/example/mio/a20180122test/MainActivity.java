package com.example.mio.a20180122test;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
                                String str = (String.valueOf(i+"/"+i1+"/"+i2));

                                tv1_act.setText(str);
                                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                        i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                                        String str = (String.valueOf(i+"/"+i1+"/"+i2));

                                        tv2_act.setText(str);
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
                                String str = (String.valueOf(i+"/"+i1+"/"+i2));

                                tv3.setText(str);
                                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                        i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                                        String str = (String.valueOf(i+"/"+i1+"/"+i2));

                                        tv4.setText(str);
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
                                String str = (String.valueOf(i+"/"+i1+"/"+i2));

                                tv3.setText(str);

                                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                        i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                                        String str = (String.valueOf(i+"/"+i1+"/"+i2));

                                        tv4.setText(str);
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



}
