package com.zh.dao.jpa;


import com.zh.pojo.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Page<User> findByDeptId(Integer deptId, Pageable pageable);

    User findByName(String name);
}
