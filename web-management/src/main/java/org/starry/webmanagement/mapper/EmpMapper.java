package org.starry.webmanagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.starry.webmanagement.pojo.Emp;

import java.util.List;

@Mapper
public interface EmpMapper {

    @Select("select e.*, d.name as dept_name from emp e left join dept d on e.dept_id = d.id " +
            "order by e.update_time limit #{start}, #{pageSize};")
    public List<Emp> list(Integer start, Integer pageSize);

    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    public Long count();
}
