package org.starry.webmanagement.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.starry.webmanagement.mapper.EmpExprMapper;
import org.starry.webmanagement.mapper.EmpMapper;
import org.starry.webmanagement.pojo.*;
import org.starry.webmanagement.service.EmpService;
import org.starry.webmanagement.utils.JwtUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

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

//    @Override
//    public boolean save(Emp emp) {
//        emp.setCreateTime(LocalDateTime.now());
//        emp.setUpdateTime(LocalDateTime.now());
//        empMapper.save(emp);
//        EmpQueryParam empQueryParam = new EmpQueryParam();
//        empQueryParam.setName(emp.getName());
//        List<Emp> rows = empMapper.list(empQueryParam);
//        Integer empId = rows.get(0).getId();
//        List<EmpExpr> exprList = emp.getExprList();
//        if (!CollectionUtils.isEmpty(exprList)) {
//            exprList.forEach(empExpr -> {
//                empExpr.setEmpId(empId);
//                empMapper.saveExpr(empExpr);
//            });
//        }
//        return true;
//    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.save(emp);
        List<EmpExpr> exprList = emp.getExprList();
        exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
        if (!CollectionUtils.isEmpty(exprList)) {
            empExprMapper.insertBatch(exprList);
        }
        return true;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean delete(List<Integer> ids) {
        empMapper.deleteByIds(ids);
        empExprMapper.deleteByEmpIds(ids);
        return true;
    }

    @Override
    public Emp getEmpById(Integer empId) {
        return empMapper.getEmpById(empId);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);

        Integer empId = emp.getId();
        empExprMapper.deleteByEmpIds(Arrays.asList(empId));

        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }
        return true;
    }

    @Override
    public List<Emp> findAll() {
        return empMapper.findAll();
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp e = empMapper.selectByUsernameAndPassword(emp);
        if(e != null){
            log.info("Login success: {}", e);
            String jwt = JwtUtils.generateJwt(Map.of("id", e.getId(), "username", e.getUsername()));
            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), jwt);
        }
        return null;
    }
}
