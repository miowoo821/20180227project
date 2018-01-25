package com.example.mio.a20180122test;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mio.a20180122test.data.Activities;

public class Activity_list_detail extends AppCompatActivity {

    TextView tv1,tv2,tv3,tv4,tv5;
    EditText ed1,ed2,ed3,ed4;

    int id;
    Activities activities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);

        tv1=(TextView)findViewById(R.id.act_detail_id);
        tv2=(TextView)findViewById(R.id.act_detail_s_d);
        tv3=(TextView)findViewById(R.id.act_detail_e_d);
        tv4=(TextView)findViewById(R.id.act_detail_f_s_d);
        tv5=(TextView)findViewById(R.id.act_detail_f_e_d);

        ed1=(EditText)findViewById(R.id.act_detail_name);
        ed2=(EditText)findViewById(R.id.act_detail_ratio);
        ed3=(EditText)findViewById(R.id.act_detail_limited);
        ed4=(EditText)findViewById(R.id.act_detail_memo);

        id=getIntent().getIntExtra("position",0);

        Log.d("TESTTTTTTTTTTTTT",String.valueOf(id));
        activities=Activity_list.dao.get_activity(id);

        tv1.setText(String.valueOf(activities._id));
        tv2.setText(String.valueOf(activities.Activity_S_D));
        tv3.setText(String.valueOf(activities.Activity_E_D));
        tv4.setText(String.valueOf(activities.Activity_F_S_D));
        tv5.setText(String.valueOf(activities.Activity_F_E_D));

        ed1.setText(String.valueOf(activities.Activity_Name));
        ed2.setText(String.valueOf(activities.Activity_F_Ratio));
        ed3.setText(String.valueOf(activities.Activity_F_Limited));
        ed4.setText(String.valueOf(activities.Activity_Memo));

    }

    public void clickacteditcheck(View v){
         String Activity_Name=ed1.getText().toString();
         int Activity_S_D=Integer.valueOf(tv2.getText().toString());
         int Activity_E_D=Integer.valueOf(tv3.getText().toString());
         String Activity_F_S_D=tv4.getText().toString();
         String Activity_F_E_D=tv5.getText().toString();
         int Activity_F_Limited=Integer.valueOf(ed3.getText().toString());
         int Activity_F_Ratio=Integer.valueOf(ed2.getText().toString());
         String Activity_Memo=ed4.getText().toString();

        Activity_list.dao.update_activity(new Activities(id,//抓本頁的id,id一定要給喔
                Activity_Name,  Activity_S_D, Activity_E_D ,
                Activity_F_S_D,  Activity_F_E_D, Activity_F_Limited,
                Activity_F_Ratio, Activity_Memo));
        Toast.makeText(Activity_list_detail.this,"修改完成",Toast.LENGTH_SHORT).show();
        finish();
    }
    public void clickactdelete(View v){
        AlertDialog.Builder builder=new AlertDialog.Builder(Activity_list_detail.this);//新增會彈出對話框的物件
        builder.setTitle("刪除確認");//對話框標題
        builder.setMessage("是否要刪除本筆資料?");//對話框訊息
        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {//設定對話況右邊的鈕為確認，並設一個監聽器，監聽點擊事件
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Activity_list.dao.delete_activity(id);
                Toast.makeText(Activity_list_detail.this,"已刪除",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();

    }
    public void clickactback(View v){
        finish();
    }
}
