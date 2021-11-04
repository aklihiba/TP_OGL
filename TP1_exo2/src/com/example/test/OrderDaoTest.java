package com.example.test;

import com.example.dao.*;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.Orderline;
import com.example.entity.Product;

import java.sql.Connection;
import java.util.*;

import static org.junit.Assert.*;

public class OrderDaoTest {

    @org.junit.Test
    public void insertOrder() {
        DatabaseConnection db = new DatabaseConnection(
                 "sa",
                 "",
                "org.h2.Driver",
                "jdbc:h2:mem:test"
        );
        Connection con = db.connect();
        db.createDb(con);
        OrderDao orderdao = new OrderDao();
        orderdao.setConn(con);
        Customer client = new Customer();
        Product p = new Product("pid");
        List<Orderline> line = new ArrayList<>();
        line.add(new Orderline(p, 2));
        Order order = new Order(1, line,client);
        orderdao.insertOrder(order);

        List<Orderline> l = orderdao.getOrderDetails(1);
        assertArrayEquals(l.toArray(), line.toArray());

        orderdao.deleteOrder(1);
        l = orderdao.getOrderDetails(1);
        assertNull(l);

        // test the exception

    }

}