package org.starry.webmanagement.mapper;

import org.apache.ibatis.annotations.*;
import org.starry.webmanagement.pojo.Emp;
import org.starry.webmanagement.pojo.EmpQueryParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

//    @Select("select e.*, d.name as dept_name from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time limit #{start}, #{pageSize};")
//    public List<Emp> list(Integer start, Integer pageSize);
//
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
//    public Long count();


    List<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    boolean save(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getEmpById(Integer empId);

    boolean update(Emp emp);

//    void saveExpr(EmpExpr empExpr);
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();
}
