package org.starry.webmanagement.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.starry.webmanagement.mapper.ClazzMapper;
import org.starry.webmanagement.pojo.Clazz;
import org.starry.webmanagement.pojo.PageResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult<Clazz> page(String name, LocalDate begin, LocalDate end, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);

        List<Clazz> rows = clazzMapper.list(name, begin, end);
        Page<Clazz> p = (Page<Clazz>) rows;
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }

    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getclazzById(Integer id) {
        return clazzMapper.getClsById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }
}
