package com.example.mio.a20180122test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Rakuten_Activity extends AppCompatActivity implements View.OnClickListener{
    ImageButton imgbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rakuten_);
        imgbtn=(ImageButton) findViewById(R.id.img_orderrecord);
        imgbtn.setOnClickListener(this);
        imgbtn=(ImageButton) findViewById(R.id.img_activityentry);
        imgbtn.setOnClickListener(this);
        imgbtn=(ImageButton) findViewById(R.id.img_switch);
        imgbtn.setOnClickListener(this);
        imgbtn=(ImageButton) findViewById(R.id.imageButton);
        imgbtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_orderrecord:
                Intent it=new Intent(Rakuten_Activity.this,OrderRecord_page.class);
                finish();
                startActivity(it);
                break;
            case R.id.img_activityentry:
                Intent it2=new Intent(Rakuten_Activity.this,Activity_list.class);
                finish();
                startActivity(it2);
                break;
            case R.id.img_switch:
                Intent it3=new Intent(Rakuten_Activity.this,Transfer_Activity.class);
                finish();
                startActivity(it3);
                //Toast.makeText(Rakuten_Activity.this, "Test3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageButton:
               finish();
//                Intent it4=new Intent(Rakuten_Activity.this,MainActivity.class);
//                startActivity(it4);
               // Toast.makeText(Rakuten_Activity.this, "Test4", Toast.LENGTH_SHORT).show();
                break;
        }
    }




}
