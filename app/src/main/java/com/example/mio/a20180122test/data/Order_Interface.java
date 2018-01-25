package com.example.mio.a20180122test.data;

import java.util.ArrayList;

/**
 * Created by mio on 2018/1/25.
 */

public interface Order_Interface {
    public boolean add(Orders orders);
    public ArrayList<Orders> get_Order_List();
    public Orders get_Order(int _id);
    public boolean update_Order(Orders orders);
    public boolean delete_Order(int _id);

}
