package org.starry.webmanagement.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.starry.webmanagement.mapper.EmpMapper;
import org.starry.webmanagement.pojo.Emp;
import org.starry.webmanagement.pojo.EmpExpr;
import org.starry.webmanagement.pojo.EmpQueryParam;
import org.starry.webmanagement.pojo.PageResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        List<Emp> rows = empMapper.list(empQueryParam);
        Page<Emp> p = (Page<Emp>) rows;
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    @Override
    public boolean save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.save(emp);
        EmpQueryParam empQueryParam = new EmpQueryParam();
        empQueryParam.setName(emp.getName());
        List<Emp> rows = empMapper.list(empQueryParam);
        Integer empId = rows.get(0).getId();
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(empId);
                empMapper.saveExpr(empExpr);
            });
        }
        return true;
    }

}
