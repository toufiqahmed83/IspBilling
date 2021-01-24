package com.isp.order.repo;

import com.isp.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Toufiq on 9/26/2019.
 */
public interface OrderRepo extends JpaRepository<Order,Long> {
}
