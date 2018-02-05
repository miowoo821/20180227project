package com.example.mio.a20180122test.data;

/**
 * Created by mio on 2018/1/25.
 */

public class Orders {
    public int _id_order;
    public String  User_Name_ID;
    public String  Order_Date;
    public int  Order_Account;
   // public int  Order_Normal_Point;
    public String  Order_Memo;

    public Orders(int _id_order,String  User_Name_ID,String Order_Date,int  Order_Account, String  Order_Memo){
        this._id_order=_id_order;
        this.User_Name_ID=User_Name_ID;
        this.Order_Date=Order_Date;
        this.Order_Account=Order_Account;
       // this.Order_Normal_Point=Order_Normal_Point;
        this.Order_Memo=Order_Memo;

    }

    public Orders(String  User_Name_ID,String Order_Date,int  Order_Account, String  Order_Memo){
        this.User_Name_ID=User_Name_ID;
        this.Order_Date=Order_Date;
        this.Order_Account=Order_Account;
       // this.Order_Normal_Point=Order_Normal_Point;
        this.Order_Memo=Order_Memo;

    }


}
