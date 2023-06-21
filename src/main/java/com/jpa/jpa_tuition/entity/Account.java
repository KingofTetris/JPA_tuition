package com.jpa.jpa_tuition.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author by KingOfTetris
 * @date 2023/6/20
 */


@Data
@Entity
@Table(name = "tb_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id") //注意 你这个主键创建出来以后 要改就要去数据库里面改了，在这里改，相当于再创建一个主键是不行的。
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
