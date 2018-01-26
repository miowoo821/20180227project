package com.example.mio.a20180122test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class OrderRecord_page extends AppCompatActivity {
    public static Activity_Interface dao;
    String  actd;
    ListView lv;
    act_order_item_Adapter adapter;
    ArrayList my_act_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_record_page);

        dao=new Activities_DAO_DB_Impl(this);//記得NEW他

        my_act_list=new ArrayList();//記得NEW他
        my_act_list=dao.get_order_List();//記得丟資料進去
        adapter=new act_order_item_Adapter(OrderRecord_page.this,my_act_list);
        Log.d("TESTTTTTTTTTTTTT",String.valueOf(my_act_list));
        lv=(ListView)findViewById(R.id.listView);
        lv.setAdapter(adapter);//lv2記得要在大家都用的到的地方findviewbyid，例如onCreate
    }
}
