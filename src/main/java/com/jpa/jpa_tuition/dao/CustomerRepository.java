package com.jpa.jpa_tuition.dao;

import com.jpa.jpa_tuition.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author by KingOfTetris
 * @date 2023/6/20
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
