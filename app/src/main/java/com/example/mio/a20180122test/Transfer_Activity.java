package com.example.mio.a20180122test;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mio.a20180122test.adapter.account_list_item_Adapter;
import com.example.mio.a20180122test.data.Account_DAO_DB;
import com.example.mio.a20180122test.data.Accounts;
import com.example.mio.a20180122test.detail.account_detail;

import java.util.ArrayList;
//GlobalVariable_User_Account預設在GlobalVariable的java檔裡，可以想辦法改
public class Transfer_Activity extends AppCompatActivity implements View.OnClickListener{
    ImageButton imgbtn;
    ListView account_list_lv;

    Account_DAO_DB ADAO;
    ArrayList my_account_list;
    account_list_item_Adapter adapter;

    GlobalVariable User;
    String GlobalVariable_User_Account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_);

        //***********全域變數*****************
        User = (GlobalVariable) getApplicationContext();//全域變數(資料庫的名字)
        GlobalVariable_User_Account= User.get_GlobalVariable_User_Account();
        //***********全域變數*****************

        imgbtn=(ImageButton) findViewById(R.id.img_orderrecord);
        imgbtn.setOnClickListener(this);
        imgbtn=(ImageButton) findViewById(R.id.img_activityentry);
        imgbtn.setOnClickListener(this);
        imgbtn=(ImageButton) findViewById(R.id.imageButton);
        imgbtn.setOnClickListener(this);
        imgbtn=(ImageButton) findViewById(R.id.img_index);
        imgbtn.setOnClickListener(this);
        account_list_lv=(ListView)findViewById(R.id.account_list_lv);

        ADAO =new Account_DAO_DB(Transfer_Activity.this,GlobalVariable_User_Account);
        my_account_list=new ArrayList();
        my_account_list=ADAO.get_Account_list();

        adapter=new account_list_item_Adapter(Transfer_Activity.this,my_account_list,GlobalVariable_User_Account);
        account_list_lv.setAdapter(adapter);


        account_list_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(Transfer_Activity.this,account_detail.class);
                //adapter=new ActlistAdapter(Activity_list.this,dao.get_activity_List());
                Log.d("TESTTTTTTTTTTTTT",String.valueOf(ADAO.get_Account_list().get(i)));
                it.putExtra("position",ADAO.get_Account_list().get(i).ID);
                //it.putExtra("position",i+1);//直接給數字不法抓到準確的id，因為有時候id不等於位置(順序)
                startActivity(it);

            }
        });
        account_list_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                String Change_DB_Name=ADAO.get_Account_list().get(i).Account_Name;
                //**********痊癒變數******************
                GlobalVariable User = (GlobalVariable)getApplicationContext();
                User.set_GlobalVariable_User_Account(Change_DB_Name);
                //***********全域變數*****************
                Toast.makeText(Transfer_Activity.this,"當前帳號修改為"+Change_DB_Name,Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.switch_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder builder=new AlertDialog.Builder(Transfer_Activity.this);
        builder.setTitle("新增帳號");
        LayoutInflater inflater=LayoutInflater.from(Transfer_Activity.this);
        final View new_add_account_item=inflater.inflate(R.layout.new_add_account_item,null);
        final EditText ed1=new_add_account_item.findViewById(R.id.new_add_account);

        builder.setPositiveButton("確定新增", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ADAO.add_account(new Accounts(ed1.getText().toString()));
                Toast.makeText(Transfer_Activity.this,"新增成功",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setView(new_add_account_item);
        builder.show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_orderrecord:
                Intent it=new Intent(Transfer_Activity.this,OrderRecord_page.class);
                finish();
                startActivity(it);
                break;
            case R.id.img_activityentry:
                Intent it2=new Intent(Transfer_Activity.this,Activity_list.class);
                finish();
                startActivity(it2);
                break;
            case R.id.imageButton:
                //Intent it3=new Intent(Transfer_Activity.this,Activity_list.class);
                finish();
                //startActivity(it3);
                //Toast.makeText(Transfer_Activity.this, "Test3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_index:
                Intent it4=new Intent(Transfer_Activity.this,Rakuten_Activity.class);
                finish();
                startActivity(it4);
                //Toast.makeText(Transfer_Activity.this, "Test4", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
