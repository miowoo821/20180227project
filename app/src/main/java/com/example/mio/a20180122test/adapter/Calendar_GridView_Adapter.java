package com.example.mio.a20180122test.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mio.a20180122test.LunarCalendar;
import com.example.mio.a20180122test.R;
import com.example.mio.a20180122test.SpecialCalendar;
import com.example.mio.a20180122test.helpler.Calendar_Helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by mio on 2018/2/26.
 */

public class Calendar_GridView_Adapter extends BaseAdapter {

    ArrayList day_Block;


    @Override
    public int getCount() {
       return dayNumber.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        Calendar_GridView_Holder calendarGridViewHolder;
//
//        if(view==null){
//            calendarGridViewHolder=new Calendar_GridView_Holder(viewGroup.getContext());
//            view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.calendar_item,viewGroup,false);
//            calendarGridViewHolder.textViewArray[0]=(TextView)view.findViewById(R.id.day_block_0_0);
//            calendarGridViewHolder.textViewArray[1]=(TextView)view.findViewById(R.id.day_block_0_1);
//            calendarGridViewHolder.textViewArray[2]=(TextView)view.findViewById(R.id.day_block_0_2);
//            calendarGridViewHolder.textViewArray[3]=(TextView)view.findViewById(R.id.day_block_1_0);
//            calendarGridViewHolder.textViewArray[4]=(TextView)view.findViewById(R.id.day_block_1_1);
//            calendarGridViewHolder.textViewArray[5]=(TextView)view.findViewById(R.id.day_block_1_2);
//
//            view.setTag(calendarGridViewHolder);
//        }
//        else {
//            calendarGridViewHolder=(Calendar_GridView_Holder)view.getTag();
//        }
//
//        return view;
//    }

    class Calendar_GridView_Holder {

        //TextView day_block_0_0,day_block_0_0,day_block_0_0,day_block_0_0,day_block_0_0,day_block_0_0,day_block_0_0

        TextView[] textViewArray;

        public Calendar_GridView_Holder(Context context) {
            int textViewCount = 6;
            textViewArray = new TextView[textViewCount];
            for(int i = 0; i < textViewCount; i++) {
                textViewArray[i] = new TextView(context);
            }
        }
    }



    private int i=1;
    private int i2=1;
    private boolean isLeapyear = false;  //是否为闰年
    private int daysOfMonth = 0;      //某月的天数
    private int dayOfWeek = 0;        //具体某一天是星期几
    private int lastDaysOfMonth = 0;  //上一个月的总天数
    private Context context;
    private String[] dayNumber = new String[42];  //一个gridview中的日期存入此数组中
    //  private static String week[] = {"周日","周一","周二","周三","周四","周五","周六"};
    private SpecialCalendar sc = null;
    private LunarCalendar lc = null;
    private Resources res = null;
    private Drawable drawable = null;

    private String currentYear = "";
    private String currentMonth = "";
    private String currentDay = "";

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
    private int currentFlag = -1;     //用于标记当天
    private int[] schDateTagFlag = null;  //存储当月所有的日程日期

    private String showYear = "";   //用于在头部显示的年份
    private String showMonth = "";  //用于在头部显示的月份
    private String animalsYear = "";
    private String leapMonth = "";   //闰哪一个月
    private String cyclical = "";   //天干地支
    //系统当前时间
    private String sysDate = "";
    private String sys_year = "";
    private String sys_month = "";
    private String sys_day = "";

    public Calendar_GridView_Adapter(){
        Date date = new Date();
        sysDate = sdf.format(date);  //当期日期
        sys_year = sysDate.split("-")[0];
        sys_month = sysDate.split("-")[1];
        sys_day = sysDate.split("-")[2];

    }

    public Calendar_GridView_Adapter(Context context,Resources rs,int jumpMonth,int jumpYear,int year_c,int month_c,int day_c){
        //this();
        this.context= context;
        sc = new SpecialCalendar();
        lc = new LunarCalendar();
        this.res = rs;

        int stepYear = year_c+jumpYear;
        int stepMonth = month_c+jumpMonth ;
        if(stepMonth > 0){
            //往下一个月滑动
            if(stepMonth%12 == 0){
                stepYear = year_c + stepMonth/12 -1;
                stepMonth = 12;
            }else{
                stepYear = year_c + stepMonth/12;
                stepMonth = stepMonth%12;
            }
        }else{
            //往上一个月滑动
            stepYear = year_c - 1 + stepMonth/12;
            stepMonth = stepMonth%12 + 12;
            if(stepMonth%12 == 0){

            }
        }

        currentYear = String.valueOf(stepYear);;  //得到当前的年份
        currentMonth = String.valueOf(stepMonth);  //得到本月 （jumpMonth为滑动的次数，每滑动一次就增加一月或减一月）
        currentDay = String.valueOf(day_c);  //得到当前日期是哪天

        getCalendar(Integer.parseInt(currentYear),Integer.parseInt(currentMonth));

    }

    public Calendar_GridView_Adapter(Context context,Resources rs,int year, int month, int day){
        this();
        this.context= context;
        sc = new SpecialCalendar();
        lc = new LunarCalendar();
        this.res = rs;
        currentYear = String.valueOf(year);;  //得到跳转到的年份
        currentMonth = String.valueOf(month);  //得到跳转到的月份
        currentDay = String.valueOf(day);  //得到跳转到的天

        getCalendar(Integer.parseInt(currentYear),Integer.parseInt(currentMonth));

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_item, parent,false);
            TextView textView = (TextView) convertView.findViewById(R.id.day_block_0_1);
            TextView textView2 = (TextView) convertView.findViewById(R.id.day_block_0_0);
            TextView textView3 = (TextView) convertView.findViewById(R.id.day_block_0_2);
            TextView textView4 = (TextView) convertView.findViewById(R.id.day_block_1_0);
            TextView textView5 = (TextView) convertView.findViewById(R.id.day_block_1_1);
            TextView textView6 = (TextView) convertView.findViewById(R.id.day_block_1_2);
           // textView.setText(String.valueOf(position));


            //*************************抓時間********************************
            Calendar calendar;

            int year;
            int month;
            int day;

            calendar=new GregorianCalendar();
            year=calendar.get(Calendar.YEAR) ;
            month=calendar.get(Calendar.MONTH)+1 ;//0~11補正1~12
            day=calendar.get(Calendar.DAY_OF_MONTH) ;
            //TextView display_mounth=(TextView)convertView.findViewById(R.id.display_month);
            //display_mounth.setText(String.valueOf(year+"年"+month+"月"));
            //*****************************************************************
            int feb_days;//2月天數
            Calendar_Helper calendarHelper;
            calendarHelper =new Calendar_Helper();

            int start_Day=calendarHelper.get_Day_of_the_week(year,month,day);
            feb_days=calendarHelper.set_feb_Day(year);
            int[] MONTH_LENGTH_LIST ={ 31, feb_days, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            Log.d("GGGG","feb_days========="+feb_days) ;
            if(position<start_Day){//寫入上個月的尾巴
                int daylast=start_Day-position-1;//取得上個月與這個月的日期差

                if(month==1){
                    textView.setText(String.valueOf(MONTH_LENGTH_LIST[11] - daylast));
                }else {
                    textView.setText(String.valueOf(MONTH_LENGTH_LIST[month-2] - daylast));
                }
            }
            if(MONTH_LENGTH_LIST[month-1]>=i && position>=start_Day){//寫入本月的日期
                textView.setText(String.valueOf(i));
                    if(i==day){
                        textView.setBackgroundColor(0xff0000ff);
                        textView.setTextColor(0xffffffff);
                        textView.setTextSize(8);
                    }
                i++;
            }

            if(position-start_Day>= MONTH_LENGTH_LIST[month-1]){//當position大於這個月的結尾時，寫入下個月的頭
                textView.setText(String.valueOf(i2));
                i2++;
            }




//            Log.d("GGGG","dayNumber"+ dayNumber.length);
//            String d = dayNumber[position].split("\\.")[0];
//            String dv = dayNumber[position].split("\\.")[1];
//
//            SpannableString sp = new SpannableString(d+"\n"+dv);
//            sp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, d.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            sp.setSpan(new RelativeSizeSpan(1.2f) , 0, d.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            if(dv != null ||dv != ""){
//                sp.setSpan(new RelativeSizeSpan(0.75f), d.length()+1, dayNumber[position].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            }
//            textView.setText(sp);




//
//            if (position < daysOfMonth + dayOfWeek && position >= dayOfWeek) {
//                // 当前月信息显示
//                textView.setTextColor(Color.BLACK);// 当月字体设黑
//                drawable = res.getDrawable(R.drawable.house_icon);
//
//            }
//            if(schDateTagFlag != null && schDateTagFlag.length >0){
//                for(int i = 0; i < schDateTagFlag.length; i++){
//                    if(schDateTagFlag[i] == position){
//                        //设置日程标记背景
//                        textView.setBackgroundResource(R.drawable.house_icon);
//                    }
//                }
//            }
//            if(currentFlag == position){
//                //设置当天的背景
//                drawable = res.getDrawable(R.drawable.house_icon);
//                textView.setBackgroundDrawable(drawable);
//                textView.setTextColor(Color.WHITE);
//            }

        }


//      sp.setSpan(new ForegroundColorSpan(Color.MAGENTA), 14, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)


//      if(position<7){
//          //设置周
//          textView.setTextColor(Color.WHITE);
//          textView.setBackgroundColor(color.search_txt_color);
//          textView.setTextSize(14);
//      }


        return convertView;
    }

    //得到某年的某月的天数且这月的第一天是星期几
    public void getCalendar(int year, int month){
        isLeapyear = sc.isLeapYear(year);              //是否为闰年
        daysOfMonth = sc.getDaysOfMonth(isLeapyear, month);  //某月的总天数
        dayOfWeek = sc.getWeekdayOfMonth(year, month);      //某月第一天为星期几
        lastDaysOfMonth = sc.getDaysOfMonth(isLeapyear, month-1);  //上一个月的总天数
        Log.d("DAY", isLeapyear+" ======  "+daysOfMonth+"  ============  "+dayOfWeek+"  =========   "+lastDaysOfMonth);
        getweek(year,month);
    }

    //将一个月中的每一天的值添加入数组dayNuber中
    private void getweek(int year, int month) {
        int j = 1;
        int flag = 0;
        String lunarDay = "";

        //得到当前月的所有日程日期(这些日期需要标记)

        for (int i = 0; i < dayNumber.length; i++) {
            // 周一
//          if(i<7){
//              dayNumber[i]=week[i]+"."+" ";
//          }
            if(i < dayOfWeek){  //前一个月
                int temp = lastDaysOfMonth - dayOfWeek+1;
                lunarDay = lc.getLunarDate(year, month-1, temp+i,false);
                dayNumber[i] = (temp + i)+"."+lunarDay;
            }else if(i < daysOfMonth + dayOfWeek){   //本月
                String day = String.valueOf(i-dayOfWeek+1);   //得到的日期
                lunarDay = lc.getLunarDate(year, month, i-dayOfWeek+1,false);
                dayNumber[i] = i-dayOfWeek+1+"."+lunarDay;
                //对于当前月才去标记当前日期
                if(sys_year.equals(String.valueOf(year)) && sys_month.equals(String.valueOf(month)) && sys_day.equals(day)){
                    //标记当前日期
                    currentFlag = i;
                }
                setShowYear(String.valueOf(year));
                setShowMonth(String.valueOf(month));
                setAnimalsYear(lc.animalsYear(year));
                setLeapMonth(lc.leapMonth == 0?"":String.valueOf(lc.leapMonth));
                setCyclical(lc.cyclical(year));
            }else{   //下一个月
                lunarDay = lc.getLunarDate(year, month+1, j,false);
                dayNumber[i] = j+"."+lunarDay;
                j++;
            }
        }

        String abc = "";
        for(int i = 0; i < dayNumber.length; i++){
            abc = abc+dayNumber[i]+":";
        }
        Log.d("DAYNUMBER",abc);


    }


    public void matchScheduleDate(int year, int month, int day){

    }

    /**
     * 点击每一个item时返回item中的日期
     * @param position
     * @return
     */
    public String getDateByClickItem(int position){
        return dayNumber[position];
    }

    /**
     * 在点击gridView时，得到这个月中第一天的位置
     * @return
     */
    public int getStartPositon(){
        return dayOfWeek+7;
    }

    /**
     * 在点击gridView时，得到这个月中最后一天的位置
     * @return
     */
    public int getEndPosition(){
        return  (dayOfWeek+daysOfMonth+7)-1;
    }

    public String getShowYear() {
        return showYear;
    }

    public void setShowYear(String showYear) {
        this.showYear = showYear;
    }

    public String getShowMonth() {
        return showMonth;
    }

    public void setShowMonth(String showMonth) {
        this.showMonth = showMonth;
    }

    public String getAnimalsYear() {
        return animalsYear;
    }

    public void setAnimalsYear(String animalsYear) {
        this.animalsYear = animalsYear;
    }

    public String getLeapMonth() {
        return leapMonth;
    }

    public void setLeapMonth(String leapMonth) {
        this.leapMonth = leapMonth;
    }

    public String getCyclical() {
        return cyclical;
    }

    public void setCyclical(String cyclical) {
        this.cyclical = cyclical;
    }

}
