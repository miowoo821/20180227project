package com.example.mio.a20180122test.data;

/**
 * Created by mio on 2018/1/25.
 */

public class Order_Act_Point {
    public int _id_Order_Act;
    public String Order_Act;
    public int Order_Act_Point;

    public Order_Act_Point(int _id_Order_Act, String Order_Act,int Order_Act_Point){
        this._id_Order_Act=_id_Order_Act;
        this.Order_Act=Order_Act;
        this.Order_Act_Point=Order_Act_Point;

    }
    public Order_Act_Point(String Order_Act,int Order_Act_Point){
        this.Order_Act=Order_Act;
        this.Order_Act_Point=Order_Act_Point;

    }
}
