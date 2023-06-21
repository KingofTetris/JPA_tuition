package com.jpa.jpa_tuition.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/6/20
 */
@Data
@Entity
@Table(name = "tb_customer")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "cust_address")
    private String custAddress;
    @Column(name = "cust_name")
    private String custName;
//    @Column(name = "account_id")
//    private Integer accountId;

    /**
     * cascade 级联(关联)
     * ALL 所有持久化操作
     * PERSIST 插入才持久化
     * MERGE  修改持久化
     * REMOVE 删除持久化
     *
     * 这里对比Mybatis就不用去写什么resultMap考虑那么多关联关系
     * 这也是Hibernate的优势
     * Customer(id=2, custAddress=null, custName=徐庶, account=Account(id=1, username=xushu, password=null))
     * 直接就能查出来
     *
     * 同样Hibernate页可以懒加载 默认是EAGER 立即加载
     * 不是所有关联对象都需要第一时间用到，这个时候就需要懒加载
     *
     * 还有个orphanRemoval属性，是级联删除数据，比如你在这里把account设置为null
     * 或者其他关联数据，那么老的那条关联数据就会被删除
     *
     * mappedBy 让本方放弃维护外键，让另一方去维护。
     * 里面的值是另一方外键的属性名
     */
    @OneToOne(mappedBy = "customer",
            cascade = CascadeType.ALL,fetch = FetchType.LAZY)//维护1对1关系 设置位all就是增删改都可以关联。
    //1对1 直接声明在本表的字段名称
    @JoinColumn(name = "account_id") //设置外键就是account的account_id
    private Account account;
    @Column(name = "test(php)")
    private String test;
    //一对多
    //声明成多的那一方的字段名称
    //并且hibernate会自动把这个外键创建在多的一方
    //并且在1这一方是不会创建出这个字段的。
    //其实很容易想象就是1个用户发了多条消息，我们不可能在用户表里面记录消息的ID
    //而是是消息表记录用户ID，形成1对多
    @OneToMany(cascade = CascadeType.ALL) //这里是All 当然就包括REMOVE
    // 那么删除也会全删，也就是用户删了，他的message也会删除
    @JoinColumn(name = "customer_id")
    private List<Message> messages;

    //单向多对多
    @ManyToMany(cascade = CascadeType.ALL) //关联操作你需要才赋值，如果你只是查就没必要设置
    //但是如果你给roles增删改了，而且还要保存，就要给持久化操作赋值。
    /**
     * 你直接在这JoinTable都能直接帮你创建出来中间表
    中间表需要两个外键，joinColumns是本表，inverseJoinColumns是关联表的名称
    没有特别设置的话 @JoinTable可以不写。*/
    @JoinTable(name = "tb_customer_role",
            joinColumns = {@JoinColumn(name = "c_id")},
            inverseJoinColumns = {@JoinColumn(name = "r_id")})
    private List<Role> roles;
}
