package org.starry.webmanagement.mapper;

import org.apache.ibatis.annotations.*;
import org.starry.webmanagement.pojo.Emp;
import org.starry.webmanagement.pojo.EmpQueryParam;

import java.util.List;
import java.util.Map;

/**
 * MyBatis mapper for employee persistence and report queries.
 */
@Mapper
public interface EmpMapper {

//    @Select("select e.*, d.name as dept_name from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time limit #{start}, #{pageSize};")
//    public List<Emp> list(Integer start, Integer pageSize);
//
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
//    public Long count();


    /**
     * Queries records by the supplied filters.
     */
    List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * Creates a new record.
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    boolean save(Emp emp);

    /**
     * Deletes records by id list.
     */
    void deleteByIds(List<Integer> ids);

    /**
     * Queries an employee by id, including work experience data.
     */
    Emp getEmpById(Integer empId);

    /**
     * Updates an existing record.
     */
    boolean update(Emp emp);

//    void saveExpr(EmpExpr empExpr);
    /**
     * Counts employees grouped by job type.
     */
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    /**
     * Counts employees grouped by gender.
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    /**
     * Queries all records.
     */
    List<Emp> findAll();

    /**
     * Finds an employee by username and password.
     */
    @Select("select id, username, name from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
