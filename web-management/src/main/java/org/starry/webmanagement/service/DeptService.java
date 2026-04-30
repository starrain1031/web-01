package org.starry.webmanagement.service;

import org.starry.webmanagement.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();

    void deleteById(Integer id);

    boolean add(Dept dept);

    boolean update(Dept dept);

    Dept getDeptById(Integer deptId);
}
