package org.starry.webmanagement.mapper;

import org.apache.ibatis.annotations.*;
import org.starry.webmanagement.pojo.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {

//    @Results(id = "deptMap", value = {
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })
    @Select("select id, name, create_time, update_time from dept order by dept.update_time desc")
    List<Dept> findAll();

    @Delete("delete from dept where id = #{deptId}")
    void deleteById(Integer deptId);

    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    boolean add(Dept dept);

    @Select("select id, name, create_time, update_time from dept where id = #{deptId}")
    Dept getDeptById(Integer deptId);

    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    boolean update(Dept dept);


}
