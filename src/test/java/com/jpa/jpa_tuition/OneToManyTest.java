package com.jpa.jpa_tuition;

import com.jpa.jpa_tuition.dao.CustomerRepository;
import com.jpa.jpa_tuition.entity.Customer;
import com.jpa.jpa_tuition.entity.Message;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author by KingOfTetris
 * @date 2023/6/20
 */

@SpringBootTest
public class OneToManyTest {
    @Autowired
    CustomerRepository repository;
    @Test
    public void test(){
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message("您好"));
        messageList.add(new Message("在吗"));
        Customer customer = new Customer();
        customer.setCustName("诸葛");
        customer.setMessages(messageList);
        repository.save(customer);
    }

    @Test
    @Transactional
    public void test2(){
        Optional<Customer> customer = repository.findById(3);
        System.out.println("=========================");
        //因为到这里你才要查account,而account这个东西是设置了懒加载的。
        //所以你上面的查询不会去查account，而且查完以后Session已经关闭了就会报错
        //所以你得加上事务注解
        //而且一对多的情况下，查多默认就是懒加载。
        System.out.println(customer);
    }
}
