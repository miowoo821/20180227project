package com.example.mio.a20180122test.data;

/**
 * Created by mio on 2018/1/25.
 */

public class Order_Act_Point {
    public int _id_Order_Act;
    public long Order_ID;
    public int Order_Act_ID;
    public String user_name_id;
    public String Order_Act;
    public int Order_Act_Point;

    public Order_Act_Point(int _id_Order_Act,long Order_ID,String user_name_id,int Order_Act_ID, String Order_Act,int Order_Act_Point){

        this._id_Order_Act=_id_Order_Act;
        this.user_name_id=user_name_id;
        this.Order_ID= Order_ID;
        this.Order_Act_ID=Order_Act_ID;
        this.Order_Act=Order_Act;
        this.Order_Act_Point=Order_Act_Point;

    }
    public Order_Act_Point(long Order_ID,String user_name_id,int Order_Act_ID,String Order_Act,int Order_Act_Point){
        this.Order_ID= Order_ID;
        this.user_name_id=user_name_id;
        this.Order_Act_ID=Order_Act_ID;
        this.Order_Act=Order_Act;
        this.Order_Act_Point=Order_Act_Point;
    }
    public Order_Act_Point(String Order_Act){
        this.Order_Act=Order_Act;
    }
}
