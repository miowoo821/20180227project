package com.example.mio.a20180122test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class account_detail extends AppCompatActivity {
int id;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);

        tv1=(TextView)findViewById(R.id.account_ID);
        id=getIntent().getIntExtra("position",0);
        Log.d("TESTTTTTTTTTTTTT",String.valueOf(id));
        tv1.setText(String.valueOf(id));
    }
}
