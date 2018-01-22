package com.example.mio.a20180122test;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton imgbtn;
    private int mYear, mMonth, mDay;
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
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);//新增一個會在MainActivity裡面彈出的Dialog物件
        builder.setTitle("新增紀錄");

        LayoutInflater inflater=LayoutInflater.from(MainActivity.this);//layout解壓縮用的，可以把res裡面的layout挖出來
        View neworder=inflater.inflate(R.layout.neworder,null);//第一個參數是要放入的Layout,第二個放null(為什麼)

        final TextView tv1=neworder.findViewById(R.id.New_order_date);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c=Calendar.getInstance();//getInstance()是抓現在的時間
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
                        i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
                        String str = (String.valueOf(i+"/"+i1+"/"+i2));
                        //tv1.setText(String.valueOf(c));
                        tv1.setText(str);
                    }
                }, mYear,mMonth, mDay).show();

            }
        });



        builder.setView(neworder);

        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
        return super.onOptionsItemSelected(item);
    }
}
