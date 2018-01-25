//package com.example.mio.a20180122test.data;
//
//import android.app.DatePickerDialog;
//import android.util.Log;
//import android.widget.DatePicker;
//import android.widget.TextView;
//
//import com.example.mio.a20180122test.Activity_list;
//
//import java.util.Calendar;
//
///**
// * Created by mio on 2018/1/25.
// */
//
//public class Date_Picker_test {
//    String mYear, mMonth, mDay,startday;
//    Calendar c;
//    public int start_day(){
//
//        c= Calendar.getInstance();
//        mYear =String.valueOf( c.get(Calendar.YEAR));
//        mMonth = String.valueOf( c.get(Calendar.MONTH));
//        mDay =String.valueOf(  c.get(Calendar.DAY_OF_MONTH));
//        //DatePickerDialog連續執行兩次，就有兩層DatePickerDialog，最上面那層就是比較後面執行的，所以先填的是後面的日期
//        //修正方法，在第一個DatePickerDialog關閉才跳出第二個
//        new DatePickerDialog(Activity_list.class, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
//                startday = (String.valueOf(i+"/"+i1+"/"+i2));
//                Log.d("GGGGGGGG1111111",startday);
//
//
//                new DatePickerDialog(Activity_list.class, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                        i1=i1+1;//幹為什麼月份是從0開始拉，所以這邊把月份手動+1
//                        startday = (String.valueOf(i+"/"+i1+"/"+i2));
//                        Log.d("GGGGGGGG2",startday);
//
//                    }
//                },Integer.valueOf( mYear), Integer.valueOf( mMonth), Integer.valueOf( mDay).show());
//            }
//        },mYear, mMonth, mDay).show();
//    }
//
//        return startday;
//    }
//}
