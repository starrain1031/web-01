package org.starry.webmanagement.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.starry.webmanagement.mapper.EmpMapper;
import org.starry.webmanagement.pojo.Emp;
import org.starry.webmanagement.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService{
    @Autowired
    private EmpMapper empMapper;

    private PageResult pageResult;

    @Override
    public List<Emp> findAll() {

        return List.of();
    }

    //    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        Long result = empMapper.count();
//        int start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//        PageResult<Emp> pageResult = new PageResult<>();
//        pageResult.setTotal(result);
//        pageResult.setRows(rows);
//        return pageResult;
//    }
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender,
                                LocalDate begin, LocalDate end) {
        PageHelper.startPage(page, pageSize);

        List<Emp> rows = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) rows;
        return new PageResult<>(p.getTotal(),p.getResult());
    }
}
