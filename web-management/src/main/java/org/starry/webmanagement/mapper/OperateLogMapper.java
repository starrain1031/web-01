package org.starry.webmanagement.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.starry.webmanagement.pojo.OperateLog;

import java.util.List;

/**
 * MyBatis mapper for operation log persistence operations.
 */
@Mapper
public interface OperateLogMapper {

    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    /**
     * Inserts a new record.
     */
    void insert(OperateLog log);

    /**
     * Queries records by the supplied filters.
     */
    @Select("select * from operate_log")
    List<OperateLog> list();
}
