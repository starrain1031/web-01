package org.starry.webmanagement.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.starry.webmanagement.mapper.ClazzMapper;
import org.starry.webmanagement.pojo.Clazz;
import org.starry.webmanagement.pojo.PageResult;
import org.starry.webmanagement.service.ClazzService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


/**
 * Default implementation of class management business logic.
 */
@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * Queries records with pagination and filters.
     */
    @Override
    public PageResult<Clazz> page(String name, LocalDate begin, LocalDate end, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);

        List<Clazz> rows = clazzMapper.list(name, begin, end);
        Page<Clazz> p = (Page<Clazz>) rows;
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    /**
     * Deletes a record by id.
     */
    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }

    /**
     * Creates a new record.
     */
    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    /**
     * Queries a class by id.
     */
    @Override
    public Clazz getclazzById(Integer id) {
        return clazzMapper.getClsById(id);
    }

    /**
     * Updates an existing record.
     */
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    /**
     * Queries all records.
     */
    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }
}
