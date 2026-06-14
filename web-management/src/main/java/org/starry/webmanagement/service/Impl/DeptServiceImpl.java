package org.starry.webmanagement.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.starry.webmanagement.mapper.DeptMapper;
import org.starry.webmanagement.pojo.Dept;
import org.starry.webmanagement.service.DeptService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Default implementation of department management business logic.
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    /**
     * Queries all records.
     */
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    /**
     * Deletes a record by id.
     */
    @Override
    public void deleteById(Integer deptId) {
        deptMapper.deleteById(deptId);
    }

    /**
     * Creates a new record.
     */
    @Override
    public boolean add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        return deptMapper.add(dept);
    }

    /**
     * Queries a department by id.
     */
    @Override
    public Dept getDeptById(Integer deptId) {
        return deptMapper.getDeptById(deptId);
    }

    /**
     * Updates an existing record.
     */
    @Override
    public boolean update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        return deptMapper.update(dept);
    }
}
