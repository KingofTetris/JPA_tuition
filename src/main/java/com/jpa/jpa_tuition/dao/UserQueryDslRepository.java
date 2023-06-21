package com.jpa.jpa_tuition.dao;

import com.jpa.jpa_tuition.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/6/20
 */
/*@Repository
//JpaRepository<User,Integer> 基本crud
//QuerydslPredicateExecutor<User> 动态sql
public interface UserQueryDslRepository
        extends JpaRepository<User,Integer>,
        QuerydslPredicateExecutor<User> {
}*/
