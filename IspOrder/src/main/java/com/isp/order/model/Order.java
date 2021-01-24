package com.isp.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Toufiq on 9/26/2019.
 */
@Entity
@Table(name = "ORD_ORDER_HEADER")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_HEADER_SEQ")
    @SequenceGenerator(name = "ORDER_HEADER_SEQ", sequenceName = "ORDER_HEADER_SEQ", allocationSize = 1)
    @Column(name = "ORDER_HEAEDER_ID")
    private Long headerId;

    @Column(name = "ORDER_NUMBER")
    private String orderNumber;




    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ORDER_DATE")//, nullable = false
    @Temporal(TemporalType.DATE)
    private Date orderDate;

}
