package com.example.mio.a20180122test;

import java.util.Date;

/**
 * Created by mio on 2018/1/22.
 */

public class Activities {
    public int  _id;
    public String Activity_Name;
    public String Activity_S_D;
    public String Activity_E_D;
    public String Activity_F_S_D;
    public String Activity_F_E_D;
    public int Activity_F_Limited;
    public int Activity_F_Ratio;
    public String Activity_Memo;

    public Activities(int _id,String Activity_Name, String Activity_S_D,String Activity_E_D ,String Activity_F_S_D, String Activity_F_E_D,int Activity_F_Limited,int Activity_F_Ratio,String Activity_Memo){
        this._id=_id;
        this.Activity_Name=Activity_Name;
        this.Activity_S_D=Activity_S_D;
        this.Activity_E_D=Activity_E_D;
        this.Activity_F_S_D=Activity_F_S_D;
        this.Activity_F_E_D=Activity_F_E_D;
        this.Activity_F_Limited=Activity_F_Limited;
        this.Activity_F_Ratio=Activity_F_Ratio;
        this.Activity_Memo=Activity_Memo;
    }
    public Activities(String Activity_Name, String Activity_S_D,String Activity_E_D ,String Activity_F_S_D, String Activity_F_E_D,int Activity_F_Limited,int Activity_F_Ratio,String Activity_Memo){

        this.Activity_Name=Activity_Name;
        this.Activity_S_D=Activity_S_D;
        this.Activity_E_D=Activity_E_D;
        this.Activity_F_S_D=Activity_F_S_D;
        this.Activity_F_E_D=Activity_F_E_D;
        this.Activity_F_Limited=Activity_F_Limited;
        this.Activity_F_Ratio=Activity_F_Ratio;
        this.Activity_Memo=Activity_Memo;
    }


}
