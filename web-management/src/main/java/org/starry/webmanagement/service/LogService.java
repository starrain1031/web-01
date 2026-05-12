package org.starry.webmanagement.service;

import org.starry.webmanagement.pojo.OperateLog;
import org.starry.webmanagement.pojo.PageResult;

public interface LogService {
    PageResult<OperateLog> page(int page, int pageSize);
}
