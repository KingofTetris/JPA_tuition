package com.jpa.jpa_tuition.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author by KingOfTetris
 * @date 2023/6/20
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sys_user") //对应表名，没有JPA会帮你自动创建出来。
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //设置主键自增
    @Column
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;
}
