package com.jpa.jpa_tuition.dao;

import com.jpa.jpa_tuition.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * @author by KingOfTetris
 * @date 2023/6/20
 */
@Repository
//JPA直接继续这个Repository对象，里面的泛型是实体类和主键的包装类型
public interface UserRepository extends JpaRepository<User,Integer> {

    //根据JPA的规则命名查询方法就不用写SQL
    //根据username查询
    List<User> findAllByUsername(String username);

    //根据两个属性and查询
    //注意如果你是多条件查询，那么参数页必须是多个。不然直接报错
    List<User> findAllByUsernameAndPassword(String username,String password);

    //模糊查询
    List<User> findAllByUsernameLike(String usernameLike);

    //Hibernate: select u1_0.id,u1_0.password,u1_0.username from sys_user u1_0
    @Query("select u from User u") //从实体类User中查询所有的列，查实体类而不是表。hibernate就是这么别扭。
    List<User> findAll();

    @Query("select u from User u where u.username = 'aT3zo5hiszAwcR'") //后面就是属性名
    List<User> findAllUserByUsername();

    //投影 就是单纯选择你想要的列名
    //那么List里面就是你想要查出来的东西类型
    @Query("select u.id from User u")
    List<Integer> findAllUser2();

    //JPA要查询，必须在指定的Repository里面
//    //返回指定多行指定列，你还是得写VO
//    @Query("select u.id,u.username from User u")
//    List<UserVo> findAllUser3();//返回的是对象数组

    //增删改你一定得加上@Modifying注解
    @Transactional
    @Modifying
    @Query("update User set username=?1,password= ?2,phone = ?3 where id = ?4")
    int updateUsernameAndPasswordAndPhoneById(String username,String password,String phone,Integer id);


    // 原生sql
    @Transactional
    @Modifying
    @Query(value = "update sys_user u set u.username=?1,u.password= ?2,u.phone = ?3 where u.id = ?4",
            nativeQuery = true)
    int updateTest(String username,String password,String phone,Integer id);

    //动态sql
    //Query by Example 只支持你的条件仅仅是字符串

}
