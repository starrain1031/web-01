package org.starry.webmanagement.service;

import org.starry.webmanagement.pojo.Emp;
import org.starry.webmanagement.pojo.EmpQueryParam;
import org.starry.webmanagement.pojo.LoginInfo;
import org.starry.webmanagement.pojo.PageResult;

import java.util.List;

/**
 * Service contract for employee management and authentication operations.
 */
public interface EmpService {

    /**
     * Queries records with pagination and filters.
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * Creates a new record.
     */
    boolean save(Emp emp);

    /**
     * Deletes records by id list.
     */
    boolean delete(List<Integer> ids);

    /**
     * Queries an employee by id, including work experience data.
     */
    Emp getEmpById(Integer empId);

    /**
     * Updates an existing record.
     */
    boolean update(Emp emp);

    /**
     * Queries all records.
     */
    List<Emp> findAll();

    /**
     * Authenticates an employee and returns login information.
     */
    LoginInfo login(Emp emp);
}
