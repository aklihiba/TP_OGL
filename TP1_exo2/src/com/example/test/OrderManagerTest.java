package com.example.test;

import com.example.controller.OrderManager;
import com.example.dao.DatabaseConnection;
import com.example.dao.OrderDao;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.Orderline;
import com.example.entity.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import  com.example.controller.*;
import org.mockito.Mockito;

public class OrderManagerTest {
    @org.junit.Test
    public void CreateOrderTrue() {

        OrderDao orderdao = Mockito.mock(OrderDao.class);
        IStockManager mockstock = Mockito.mock(IStockManager.class);
        IPayementManager mockpayment = Mockito.mock(IPayementManager.class);
        OrderManager manager = new OrderManager(orderdao,mockstock,mockpayment);

        Customer client = new Customer();
        Product p1 = new Product("pid1");

        List<Orderline> line1 = new ArrayList<>();

        line1.add(new Orderline(p1, 2));
        line1.add(new Orderline(p1, 2));

        Order order1 = new Order(1, line1, client);

        Mockito.when(mockstock.getProductQte(p1)).thenReturn(10);
        assertTrue(manager.createOrder(order1));
        Mockito.verify(mockstock, Mockito.times(2)).removeProductStock(p1,2);

    }

    @org.junit.Test
    public void CreateOrderFalse() {

        OrderDao orderdao = Mockito.mock(OrderDao.class);
        IStockManager mockstock = Mockito.mock(IStockManager.class);
        IPayementManager mockpayment = Mockito.mock(IPayementManager.class);
        OrderManager manager = new OrderManager(orderdao,mockstock,mockpayment);

        Customer client = new Customer();
        Product p1 = new Product("pid1");

        List<Orderline> line1 = new ArrayList<>();

        line1.add(new Orderline(p1, 20));

        Order order1 = new Order(1, line1, client);

        Mockito.when(mockstock.getProductQte(p1)).thenReturn(10);
        assertFalse(manager.createOrder(order1));

    }

    @org.junit.Test
    public void cancelOrderTrue() {

        OrderDao orderdao = Mockito.mock(OrderDao.class);
        IStockManager mockstock = Mockito.mock(IStockManager.class);
        IPayementManager mockpayment = Mockito.mock(IPayementManager.class);
        OrderManager manager = new OrderManager(orderdao,mockstock,mockpayment);

        Customer client = new Customer();
        Product p1 = new Product("pid1");

        List<Orderline> line1 = new ArrayList<>();

        line1.add(new Orderline(p1, 2));
        line1.add(new Orderline(p1, 2));
        line1.add(new Orderline(p1, 2));

        Order order1 = new Order(1, line1, client);

        Mockito.when(orderdao.getOrderDetails(1)).thenReturn(line1);
        assertTrue(manager.cancelOrder(1));
        Mockito.verify(mockstock, Mockito.times(3)).addProductStock(p1,2);

    }

    @org.junit.Test
    public void cancelOrderFalse() {

        OrderDao orderdao = Mockito.mock(OrderDao.class);
        IStockManager mockstock = Mockito.mock(IStockManager.class);
        IPayementManager mockpayment = Mockito.mock(IPayementManager.class);
        OrderManager manager = new OrderManager(orderdao,mockstock,mockpayment);

        Mockito.when(mockpayment.isPaid(2)).thenReturn(Boolean.TRUE);
        assertFalse(manager.cancelOrder(2));

    }

}