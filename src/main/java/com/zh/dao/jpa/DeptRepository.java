package com.zh.dao.jpa;


import com.zh.pojo.po.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository<Dept, Integer> {
}
