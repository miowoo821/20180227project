package com.example.mio.a20180122test;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mio.a20180122test.data.Activities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Activity_list extends AppCompatActivity implements View.OnClickListener{
    ImageButton imgbtn;
    public static Activity_Interface dao;
    String  actd;
    ListView lv2;
    ActlistAdapter adapter;
    ArrayList my_act_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        imgbtn=(ImageButton) findViewById(R.id.img_orderrecord);
        imgbtn.setOnClickListener(this);
        imgbtn=(ImageButton) findViewById(R.id.imageButton);
        imgbtn.setOnClickListener(this);
        imgbtn=(ImageButton) findViewById(R.id.img_switch);
        imgbtn.setOnClickListener(this);
        imgbtn=(ImageButton) findViewById(R.id.img_index);
        imgbtn.setOnClickListener(this);

        dao=new Activities_DAO_DB_Impl(this);//記得NEW他

        my_act_list=new ArrayList();//記得NEW他
        my_act_list=dao.get_activity_List();//記得丟資料進去
        adapter=new ActlistAdapter(Activity_list.this,my_act_list);
        Log.d("TESTTTTTTTTTTTTT",String.valueOf(my_act_list));
        lv2=(ListView)findViewById(R.id.listView2);
        lv2.setAdapter(adapter);//lv2記得要在大家都用的到的地方findviewbyid，例如onCreate

        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(Activity_list.this,Activity_list_detail.class);
                //adapter=new ActlistAdapter(Activity_list.this,dao.get_activity_List());
                Log.d("TESTTTTTTTTTTTTT",String.valueOf(dao.get_activity_List().get(i)));
                it.putExtra("position",dao.get_activity_List().get(i)._id);
                //it.putExtra("position",i+1);//直接給數字不法抓到準確的id，因為有時候id不等於位置(順序)
                startActivity(it);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_orderrecord:
                Intent it=new Intent(Activity_list.this,OrderRecord_page.class);
                finish();
                startActivity(it);
                break;
            case R.id.imageButton:
                finish();
                break;
            case R.id.img_switch:
                Intent it3=new Intent(Activity_list.this,Transfer_Activity.class);
                finish();
                startActivity(it3);
               // Toast.makeText(Activity_list.this, "Test3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_index:
                Intent it4=new Intent(Activity_list.this,Rakuten_Activity.class);
                finish();
                startActivity(it4);
                //Toast.makeText(Activity_list.this, "Test4", Toast.LENGTH_SHORT).show();
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

        AlertDialog.Builder builder=new AlertDialog.Builder(Activity_list.this);
        builder.setTitle("登錄活動");
        LayoutInflater inflater=LayoutInflater.from(Activity_list.this);
        final View newactivity=inflater.inflate(R.layout.newactivity,null);
        lv2=(ListView)findViewById(R.id.listView2);//這邊只有public boolean onOptionsItemSelected(MenuItem item) {裡面的人能用
        final TextView tv1=newactivity.findViewById(R.id.act_start_date);
        final TextView tv2=newactivity.findViewById(R.id.act_end_date);
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

        tv1.setOnClickListener(new View.OnClickListener() {
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
                new DatePickerDialog(Activity_list.this, new DatePickerDialog.OnDateSetListener() {
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
                        tv1.setText(actd);

                        Log.d("GGGGGGGG1111111",tv1.getText().toString());

                        new DatePickerDialog(Activity_list.this, new DatePickerDialog.OnDateSetListener() {
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
                                tv2.setText(actd);
                                Log.d("GGGGGGGG222222222",tv2.getText().toString());
                            }
                        },mYear, mMonth, mDay).show();
                    }
                },mYear, mMonth, mDay).show();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            int mYear, mMonth, mDay;
            @Override
            public void onClick(View view) {

                Calendar c=Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(Activity_list.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                        String str = (String.valueOf(i+"/"+i1+"/"+i2));

                        tv1.setText(str);
                        new DatePickerDialog(Activity_list.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                                String str = (String.valueOf(i+"/"+i1+"/"+i2));

                                tv2.setText(str);
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
                new DatePickerDialog(Activity_list.this,android.R.style.Theme_Holo_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                        String str = (String.valueOf(i+"/"+i1+"/"+i2));

                        tv3.setText(str);
                        new DatePickerDialog(Activity_list.this, new DatePickerDialog.OnDateSetListener() {
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
                new DatePickerDialog(Activity_list.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                        String str = (String.valueOf(i+"/"+i1+"/"+i2));

                        tv3.setText(str);

                        new DatePickerDialog(Activity_list.this, new DatePickerDialog.OnDateSetListener() {
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

        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {

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
                Toast.makeText(Activity_list.this,"新增成功",Toast.LENGTH_SHORT).show();
                onResume();
            }

        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }

        });

        builder.setView(newactivity);
        builder.show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
        Log.d("TESTTTTTTTTTTTTT",String.valueOf(dao.get_activity_List()));
//        adapter=new ActlistAdapter(Activity_list.this,dao.get_activity_List());
//       lv2.setAdapter(adapter);//lv2記得要在大家都用的到的地方findviewbyid
//這兩行上移
    }
    public void refreshData(){//重抓，會不會沒效率呢？

        my_act_list=dao.get_activity_List();//記得丟資料進去
        adapter=new ActlistAdapter(Activity_list.this,my_act_list);
        lv2.setAdapter(adapter);
        adapter.notifyDataSetChanged();//一定要加這個
    }
}
