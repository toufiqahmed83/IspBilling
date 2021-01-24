package com.isp.order;

import com.isp.order.model.Order;
import com.isp.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Toufiq on 9/26/2019.
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    public List<Order> findAll()
    {
        return this.orderRepo.findAll();
    };
}
