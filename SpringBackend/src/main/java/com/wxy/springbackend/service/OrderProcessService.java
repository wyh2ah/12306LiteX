package com.wxy.springbackend.service;

import com.wxy.springbackend.repository.OrderProcessRepositroy;
import org.springframework.stereotype.Service;

@Service
public class OrderProcessService {
    private OrderProcessRepositroy orderProcessRepositroy;
    public OrderProcessService(OrderProcessRepositroy orderProcessRepositroy) {
        this.orderProcessRepositroy = orderProcessRepositroy;
    }

    public String payment(int ticket){
        return orderProcessRepositroy.payment(ticket);
    }
}
