package org.starry.webmanagement.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.starry.webmanagement.mapper.DeptMapper;
import org.starry.webmanagement.pojo.Dept;
import org.starry.webmanagement.service.DeptService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer deptId) {
        deptMapper.deleteById(deptId);
    }

    @Override
    public boolean add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        return deptMapper.add(dept);
    }

    @Override
    public Dept getDeptById(Integer deptId) {
        return deptMapper.getDeptById(deptId);
    }

    @Override
    public boolean update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        return deptMapper.update(dept);
    }
}
