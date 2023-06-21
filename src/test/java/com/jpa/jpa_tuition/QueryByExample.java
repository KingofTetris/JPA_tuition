package com.jpa.jpa_tuition;

import com.jpa.jpa_tuition.dao.UserRepository;
import com.jpa.jpa_tuition.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/6/20
 */

@SpringBootTest
public class QueryByExample {

    @Autowired
    UserRepository userRepository;
    @Test
    public void test(){
        //动态字符串条件 Example， 只支持字符串！
       User user = new User();
       user.setUsername("z2UCmZ13USI");
       user.setPassword("ZSradV79");
       Example<User> example = Example.of(user);
       List<User> all = userRepository.findAll(example);
        System.out.println(all);
    }

    @Test
    public void test2(){
        //条件匹配器
        User user = new User();
        user.setUsername("z");
        user.setPassword("9");
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("username") //对指定的属性忽略大小写，你不写就是全部忽略 属性首字母小写
//                .withIgnorePaths("password")   //设置忽略的属性
                //.withStringMatcher(ExampleMatcher.StringMatcher.ENDING); //对所有按照结尾匹配
                .withMatcher("password",m-> m.contains());//查询密码包含9的数据
        Example<User> example = Example.of(user,matcher); //把条件放大example里面
        //和LambdaQueryWrapper其实差不多
        Page<User> all = userRepository.findAll(example, PageRequest.of(0, 10));
        all.getContent().forEach(System.out::println);
    }
}
