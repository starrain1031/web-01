package org.starry.webmanagement.service;

import org.starry.webmanagement.pojo.Emp;
import org.starry.webmanagement.pojo.PageResult;

import java.util.List;

public interface EmpService {
    List<Emp> findAll();

    PageResult<Emp> page(Integer page, Integer pageSize);
}
