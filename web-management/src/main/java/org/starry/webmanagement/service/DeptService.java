package org.starry.webmanagement.service;

import org.starry.webmanagement.pojo.Dept;

import java.util.List;

/**
 * Service contract for department management operations.
 */
public interface DeptService {
    /**
     * Queries all records.
     */
    List<Dept> findAll();

    /**
     * Deletes a record by id.
     */
    void deleteById(Integer id);

    /**
     * Creates a new record.
     */
    boolean add(Dept dept);

    /**
     * Updates an existing record.
     */
    boolean update(Dept dept);

    /**
     * Queries a department by id.
     */
    Dept getDeptById(Integer deptId);
}
