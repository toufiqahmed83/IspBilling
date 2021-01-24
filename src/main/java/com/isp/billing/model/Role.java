package com.isp.billing.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
/**
 * Created by Toufiq on 7/25/2019.
 */
@Entity
@Table(name = "ROLE")
//@Getter @Setter @NoArgsConstructor
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long id;
    @Column(name = "role")
    private String role;
}
