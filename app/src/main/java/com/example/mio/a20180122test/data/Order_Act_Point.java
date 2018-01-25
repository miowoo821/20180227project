package com.example.mio.a20180122test.data;

/**
 * Created by mio on 2018/1/25.
 */

public class Order_Act_Point {
    public int _id_Order_Act;
    public long Order_ID;
    public String Order_Act;
    public int Order_Act_Point;

    public Order_Act_Point(int _id_Order_Act,long Order_ID, String Order_Act,int Order_Act_Point){

        this._id_Order_Act=_id_Order_Act;
        this.Order_ID= Order_ID;
        this.Order_Act=Order_Act;
        this.Order_Act_Point=Order_Act_Point;

    }
    public Order_Act_Point(long Order_ID,String Order_Act,int Order_Act_Point){
        this.Order_ID= Order_ID;
        this.Order_Act=Order_Act;
        this.Order_Act_Point=Order_Act_Point;

    }
}
