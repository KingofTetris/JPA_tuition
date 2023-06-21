package com.jpa.jpa_tuition;

import com.jpa.jpa_tuition.dao.CustomerRepository;
import com.jpa.jpa_tuition.dao.RoleRepository;
import com.jpa.jpa_tuition.entity.Customer;
import com.jpa.jpa_tuition.entity.Role;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author by KingOfTetris
 * @date 2023/6/20
 */

@SpringBootTest
public class ManyToManyTest {

    @Autowired
    CustomerRepository repository;

    @Autowired
    RoleRepository roleRepository;
    @Test
    @Transactional //有慢查询，有增删改你就得加上事务注解，大部分时候都得加上他
    @Commit //因为这里是单元测试，有增删改你就得加上Commit 不然不会提交。
    //普通的业务不用加，因为测试默认是没必要入库的。
    public void test1(){
        Customer customer = new Customer();
        customer.setId(12);
        customer.setCustName("贾诩");
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2).get());
        roles.add(roleRepository.findById(1).get());
        customer.setRoles(roles);//设置用户身份
        repository.save(customer);
    }

    //查询
    @Test
    @Transactional
    public void test2(){
        System.out.println(repository.findById(4));
    }

    //删除

    /**
     * 有坑，多对多关系做删除有点复杂。
     * 比如你删除12号用户，
     * 但是他和用户表是多对多关系。
     * 他就得先去找tb_customer_role这张表里面和14关联的角色ID
     * 得先把这张表里面的关系删掉，才能正常删除。
     */
    @Test
    @Transactional
    @Commit
    public void test3(){
        Optional<Customer> customer = repository.findById(12);
        repository.delete(customer.get());
    }
}
