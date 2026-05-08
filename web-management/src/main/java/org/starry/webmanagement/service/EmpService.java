package org.starry.webmanagement.service;

import org.starry.webmanagement.pojo.Emp;
import org.starry.webmanagement.pojo.EmpQueryParam;
import org.starry.webmanagement.pojo.PageResult;

import java.util.List;

public interface EmpService {

    PageResult<Emp> page(EmpQueryParam empQueryParam);

    boolean save(Emp emp);

    boolean delete(List<Integer> ids);

    Emp getEmpById(Integer empId);

    boolean update(Emp emp);

    List<Emp> findAll();
}
