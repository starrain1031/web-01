package org.starry.webmanagement.service;

import org.starry.webmanagement.pojo.Emp;
import org.starry.webmanagement.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    List<Emp> findAll();

    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);
}
