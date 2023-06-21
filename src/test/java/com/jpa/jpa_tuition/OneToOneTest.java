package com.jpa.jpa_tuition;

import com.jpa.jpa_tuition.dao.CustomerRepository;
import com.jpa.jpa_tuition.entity.Account;
import com.jpa.jpa_tuition.entity.Customer;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * @author by KingOfTetris
 * @date 2023/6/20
 */
@SpringBootTest
public class OneToOneTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void test(){
        Account account = new Account();
        account.setUsername("xushu");
        Customer customer = new Customer();
        customer.setCustName("徐庶");
        customer.setAccount(account);
        customerRepository.save(customer); //存入一个用户
    }
    @Test
    public void test1(){
        customerRepository.findById(2).ifPresent(System.out::println);
    }
    @Test
    //演示懒加载
    //为什么懒加载要加事务
    //当通过repository调用完查询方法，session就会立即关闭。
    //一旦session关闭，就不能查询了
    //加了事务以后 就能让session到整个方法之后关闭。
    @Transactional
    public void test2(){
        Optional<Customer> customer = customerRepository.findById(2);
        System.out.println("==============================");
        System.out.println(customer.get());
    }


    @Test
    public void test3(){
        //改
        Customer customer = new Customer();
        customer.setId(2);
        customer.setCustAddress("FUZHOU");
        customer.setAccount(null);
        customer.setTest("测试用力asd");
        customerRepository.save(customer);//改也是通过save来更新
    }
}
