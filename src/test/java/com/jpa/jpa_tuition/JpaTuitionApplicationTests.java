package com.jpa.jpa_tuition;

import com.jpa.jpa_tuition.dao.UserRepository;
import com.jpa.jpa_tuition.entity.User;
import com.jpa.jpa_tuition.utils.StringUtils;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@SpringBootTest
class JpaTuitionApplicationTests {

    @Autowired
    UserRepository userRepository;

    Random random = new Random();
    //插入
    @Test
    public void test1(){
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = new User();
//            随机生成长度在6到15之间的字符串
            user.setUsername(StringUtils.generateRandomString(random.nextInt(6,15)));
            user.setPassword(StringUtils.generateRandomString(random.nextInt(6,15)));
                        userList.add(user);
        }
        userRepository.saveAll(userList);
    }

    @Test
    public void testUpdate(){
        for (int i = 1; i <= 100; i++) {
            String username = StringUtils.generateRandomString(random.nextInt(6, 15));
            String password = StringUtils.generateRandomString(random.nextInt(6, 15));
            String phone = StringUtils.generateRandomPhoneNumber();
            userRepository.updateUsernameAndPasswordAndPhoneById(username,password,phone,i);
        }
    }
    //查询
    @Test
    public void test2(){
        //如果存在则输出
        userRepository.findById(random.nextInt(200)).ifPresent(System.out::println);
    }
    //改
   /* @Test
    public void test3(){
        userRepository.
    }*/
    //删
    @Test
    public void test4(){
        userRepository.deleteById(100);
    }

    //分页查询
    @Test
    public void testPageable(){
        //jpa这点很怪，是从第0页开始
        //打印第四页的10条记录
        //带分页的JPA查询，就是把分页条件放进去
        Page<User> all = userRepository.findAll(PageRequest.of(3, 10));
        System.out.println(all.getTotalElements()); //100条数据
        System.out.println(all.getTotalPages()); //10页
        System.out.println(all.getContent()); //当前页的所有内容
        System.out.println(all.hasPrevious());
        System.out.println(all.hasPrevious());
    }
    //排序查询
    @Test
    public void testSort(){
        List<User> username = userRepository.findAll(Sort.by("username").descending());//按照用户名降序
        username.forEach(System.out::println);
    }

    //根据条件查询
    @Test
    public void test5(){
        //精准查询
        userRepository.findAllByUsername("2SxZCP").forEach(System.out::println);
        System.out.println("================================================");
        //模糊查询 不要忘了加上% 不然就变成精确查询了
        userRepository.findAllByUsernameLike("%31%").forEach(System.out::println);
    }

    @Test
    public void test6(){
        //使用@Query注解 自己写jpql
        userRepository.findAll().forEach(System.out::println);
        userRepository.findAllUser2().forEach(System.out::println);
    }
    @Test
    public void test7(){
        userRepository.updateTest("asdasd","qweweqwe","13321312",55);
    }



}
