package com.example.mio.a20180122test;

import com.example.mio.a20180122test.data.Order_Interface;
import com.example.mio.a20180122test.data.Orders;

import java.util.ArrayList;

/**
 * Created by mio on 2018/1/25.
 */

public class Orders_DAO_DB_Impl implements Order_Interface {
    @Override
    public boolean add(Orders orders) {
        return false;
    }

    @Override
    public ArrayList<Orders> get_Order_List() {
        return null;
    }

    @Override
    public Orders get_Order(int _id) {
        return null;
    }

    @Override
    public boolean update_Order(Orders orders) {
        return false;
    }

    @Override
    public boolean delete_Order(int _id) {
        return false;
    }
}
