package com.test.springbootapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter @Setter
public class Chicken {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (unique = true)
    private String brand;

    @Column
    private String name;

    @Column
    private BigInteger price;
}
