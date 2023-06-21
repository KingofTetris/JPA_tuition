package com.jpa.jpa_tuition.dao;

import com.jpa.jpa_tuition.entity.Message;
import com.jpa.jpa_tuition.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author by KingOfTetris
 * @date 2023/6/20
 */

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
}
