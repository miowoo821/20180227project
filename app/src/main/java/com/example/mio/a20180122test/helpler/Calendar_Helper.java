package com.example.mio.a20180122test.helpler;

import com.example.mio.a20180122test.adapter.Calendar_ViewPagerAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by mio on 2018/2/26.
 */

public class Calendar_Helper {
    private Calendar_ViewPagerAdapter calendarViewPagerAdapter;
    private List calendar_baseadapterList=new ArrayList<>();
    int feb_days;//2月天數
    int DAY_2000_1_1 = 5;//1999/12/31是星期五
    int day_after_2000_1_1=0;
    Calendar calendar;
    int start_Day_of_the_week;//該月份的第一天是星期幾
    int Days_After_2000_1_1;//從2000/1/1開始之後過了幾天
    int[] MONTH_LENGTH_LIST ={ 31, feb_days, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int year;
    int month;
    int day;



    int whatDay = start_Day_of_the_week+1;//求輸入的該年月的一號是星期幾
    int textDateCount = 1 + whatDay;//星期幾+1=>(甚麼意思?)=>解：因為禮拜整除=0，所以+1把範圍從0~6變成1~7
    int textDateCountLast = whatDay;//設為禮拜幾的前面幾格

    public String[] setCalendar_Helper(){
        calendar=new GregorianCalendar();
        year=calendar.get(Calendar.YEAR) ;
        month=calendar.get(Calendar.MONTH)+1 ;//0~11補正1~12
        day=calendar.get(Calendar.DAY_OF_MONTH) ;
        setYearMonthDay();
        start_Day_of_the_week=get_Day_of_the_week(year,month, day);
        int whatDay = start_Day_of_the_week+1;//求輸入的該年月的一號是星期幾
        int textDateCount = whatDay;//星期幾+1=>(甚麼意思?)=>解：因為禮拜整除=0，所以+1把範圍從0~6變成1~7(20180211把+1刪除)
        int textDateCountLast = whatDay;
        String[] CalendarList=new String[168];

        if(month==1)//如果輸入的月份是1月
        {
            for (int i = MONTH_LENGTH_LIST[11]; 1 > 0; i--)//令i等於12月的天數(因為1月的上個月就是12月)，然後無窮迴圈到執行break
            {
                if (textDateCountLast > 0)//迴圈跑出有幾個上個月的天數(透過本月1號是星期幾來找出)。不能大於本月的起始星期
                {
                    // CalendarList[textDateCountLast]=String.valueOf(i);
                    CalendarList[(textDateCountLast-1)*4]=String.valueOf(i);

                    for(int i2=textDateCountLast*4+2;i2<=(textDateCountLast+1)*4;i2++){
                        //上面的textDateCountLast*4+2;是抓上個月記事欄
                        CalendarList[i2-5]=String.valueOf("note"+String.valueOf(i2-5));
                    }
                    textDateCountLast--;//每執行一次就-1，最後一個執行的是本星期的起始點
                }
                else
                {
                    break;
                }
            }
        }
        else
        {
            for (int i = MONTH_LENGTH_LIST[month - 2]; 1 > 0; i--)//如果輸入的月份不是1月，則跑一個上個月的迴圈(二月跑一月，三月跑二月)
            {
                if (textDateCountLast > 0)
                {
                    CalendarList[(textDateCountLast-1)*4]=String.valueOf(i);
                    for(int i2=textDateCountLast*4+2;i2<=(textDateCountLast+1)*4;i2++){
                        //上面的textDateCountLast*4+2;是抓上個月記事欄
                        CalendarList[i2-5]=String.valueOf("note"+String.valueOf(i2-5));
                    }
                    textDateCountLast--;
                }
                else
                {
                    break;
                }
            }
            String str="";
            for(int X=0;X<168;X++) {

                str = str+","+CalendarList[X];
            }
        }
        for(int i=1; i<=MONTH_LENGTH_LIST[month-1]; ++i)//抓出陣列裡面對應的月份(從0開始所以要-1)，接著從1號開始迴圈到該月份的最後一天
        {

            CalendarList[(textDateCount)*4]=String.valueOf(i);
            for(int i2=textDateCount*4+1;i2<=(textDateCount+1)*4;i2++){
                //上面的textDateCountLast*4+2;是抓上個月記事欄
                CalendarList[i2]=String.valueOf("note"+String.valueOf(i2));
            }
            textDateCount++;//往下一個DAY
        }
        int newMonthDate = 1;
        for(int i = whatDay + MONTH_LENGTH_LIST[month-1]+1; i<=42; ++i){
            CalendarList[(i-1)*4]=String.valueOf(newMonthDate);
            for(int i2=(i-1)*4+2;i2<=(i)*4;i2++) {
                CalendarList[i2-1] = String.valueOf("note" + String.valueOf(i2-1));
            }
            newMonthDate++;
        }

        return CalendarList;
    }
    public int get_Day_of_the_week(int year,int month,int day){//求某日期是星期幾

        DAY_2000_1_1=5;
        for (int i = 2000; i < year ; i++) {//迴圈次數等於參數的年距離2000年有幾年，進而求出要加幾天
            if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {//判斷這一年要不要潤
                DAY_2000_1_1 = (DAY_2000_1_1 + 366) % 7;//潤的話這年就是+366天，接著取7餘數判斷出是星期幾
            } else {
                DAY_2000_1_1 = (DAY_2000_1_1 + 365) % 7;////不潤的話這年就是+365天，接著取7餘數判斷出是星期幾
            }
            //每次回圈決定下一年的  是星期幾
        }

        for(int i=0; i<month-1; i++){
            DAY_2000_1_1 += MONTH_LENGTH_LIST[i];
        }

        int Day_of_the_week =(DAY_2000_1_1)%7;
        return Day_of_the_week;
    }
    public int get_Days_After_2000_1_1(int year,int month,int day){//求某日期是從2000/1/1號後過了幾天

        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {//求那年的二月有幾天
            feb_days = 29;

        } else {
            feb_days = 28;

        }
        day_after_2000_1_1=0;

        for(int i = 2000; i < year; i++)//得到年分
        {
            if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                //      Log.d("TEST,","day_after_2000_1_1========================"+day_after_2000_1_1);
                day_after_2000_1_1 += 366;

            } else {
                day_after_2000_1_1 += 365;
            }
        }
        for(int i=0; i<month-1; i++){//得到月份的開頭
            day_after_2000_1_1 += MONTH_LENGTH_LIST[i];
        }

        return day_after_2000_1_1;
    }
    public void setYearMonthDay(){
        if( (year%400==0) || ( year%4==0 && year%100!=0 ) )
        {
            MONTH_LENGTH_LIST[1] = 29;

        }
        else
        {
            MONTH_LENGTH_LIST[1] = 28;

        }
    }
    public int set_feb_Day(int Y){
        int feb_Day;
        if( (Y%400==0) || ( Y%4==0 && Y%100!=0 ) )
        {
            feb_Day = 29;

        }
        else
        {
            feb_Day = 28;

        }
        return feb_Day;
    }

}
