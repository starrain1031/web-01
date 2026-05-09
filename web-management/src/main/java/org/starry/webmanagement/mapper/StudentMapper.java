package org.starry.webmanagement.mapper;


import org.apache.ibatis.annotations.*;
import org.starry.webmanagement.pojo.Student;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Student student);

    List<Student> list(String name, Integer degree, Integer clazzId);

    void update(Student student);

    void delete(List<Integer> ids);

    @Update("update stu set violation_count = violation_count + 1 , " +
            "violation_score = violation_score + #{score} , update_time = now() where id = #{id}")
    void violationHandle(Integer id, Integer score);

    @MapKey("name")
    List<Map<String, Object>> countStudentDegreeData();

    @Select("select c.name cname , count(s.id) scount from cls c left join stu s " +
            "on s.clazz_id = c.id group by c.name order by count(s.id) desc ")
    List<Map<String, Object>> getStudentCount();

    @Select("select * from stu where id = #{id}")
    Student getById(Integer id);

//    void insertBatch(List<Student> studentList);
}
