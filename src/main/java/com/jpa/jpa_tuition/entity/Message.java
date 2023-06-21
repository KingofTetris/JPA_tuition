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
@Entity
@Table(name = "tb_message")
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "info")
    private String info;

    //设置成merge就只会改，不会增加用户
    //但是设置成merge你就必须要保证你得填入你要插入信息的主键。
    //而PERSIST反过来，得主键不冲突才能插
    //REMOVE的使用要小心一点，当外键全部删完的时候，关联数据也会被删除。
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REMOVE}) //因为在Customer那边加上了OneToMany 这里的ManyToOne 你不加也可以
    @JoinColumn(name = "customer_id")
    private Customer customer;
   /* //你要写也可以 不写也行。都会创建。
    @Column(name = "customer_id")
    private Integer customerId;*/
    //单纯用info构造
   public Message(String info){
        this.info = info;
    }

    public Message(String info,Customer customer){
       this.info = info;
       this.customer = customer;
    }


    //小心不要写customer.toString()，不然就死循环了。
    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", info='" + info + '\'' +
                '}';
    }
}
