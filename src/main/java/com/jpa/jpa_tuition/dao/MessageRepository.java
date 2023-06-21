package com.jpa.jpa_tuition.dao;

import com.jpa.jpa_tuition.entity.Customer;
import com.jpa.jpa_tuition.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author by KingOfTetris
 * @date 2023/6/20
 */

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {
}
