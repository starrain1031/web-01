package org.starry.webmanagement.mapper;


import org.apache.ibatis.annotations.*;
import org.starry.webmanagement.pojo.Student;

import java.util.List;
import java.util.Map;

/**
 * MyBatis mapper for student persistence and report queries.
 */
@Mapper
public interface StudentMapper {

    /**
     * Inserts a new record.
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Student student);

    /**
     * Queries records by the supplied filters.
     */
    List<Student> list(String name, Integer degree, Integer clazzId);

    /**
     * Updates an existing record.
     */
    void update(Student student);

    /**
     * Deletes records by id list.
     */
    void delete(List<Integer> ids);

    @Update("update stu set violation_count = violation_count + 1 , " +
            "violation_score = violation_score + #{score} , update_time = now() where id = #{id}")
    /**
     * Updates student violation count and score.
     */
    void violationHandle(Integer id, Integer score);

    /**
     * Counts students grouped by degree.
     */
    @MapKey("name")
    List<Map<String, Object>> countStudentDegreeData();

    @Select("select c.name cname , count(s.id) scount from cls c left join stu s " +
            "on s.clazz_id = c.id group by c.name order by count(s.id) desc ")
    /**
     * Counts students grouped by class.
     */
    List<Map<String, Object>> getStudentCount();

    /**
     * Queries a record by id.
     */
    @Select("select * from stu where id = #{id}")
    Student getById(Integer id);

//    void insertBatch(List<Student> studentList);
}
