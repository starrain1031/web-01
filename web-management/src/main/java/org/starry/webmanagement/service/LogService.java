package org.starry.webmanagement.service;

import org.starry.webmanagement.pojo.OperateLog;
import org.starry.webmanagement.pojo.PageResult;

/**
 * Service contract for operation log query operations.
 */
public interface LogService {
    /**
     * Queries records with pagination and filters.
     */
    PageResult<OperateLog> page(int page, int pageSize);
}
