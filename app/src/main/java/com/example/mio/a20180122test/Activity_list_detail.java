package com.example.mio.a20180122test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

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
}
