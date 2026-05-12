package org.starry.webmanagement.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.starry.webmanagement.mapper.OperateLogMapper;
import org.starry.webmanagement.pojo.OperateLog;
import org.starry.webmanagement.pojo.PageResult;
import org.starry.webmanagement.service.LogService;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageResult<OperateLog> page(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);

        List<OperateLog> rows = operateLogMapper.list();
        Page<OperateLog> p = (Page<OperateLog>) rows;
        return new PageResult<>(p.getTotal(),p.getResult());
    }
}
