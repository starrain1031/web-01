package org.starry.webmanagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.starry.webmanagement.pojo.Clazz;

import java.time.LocalDate;
import java.util.List;

/**
 * MyBatis mapper for class persistence operations.
 */
@Mapper
public interface ClazzMapper {

    /**
     * Queries records by the supplied filters.
     */
    List<Clazz> list(String name, LocalDate begin, LocalDate end);

    /**
     * Deletes a record by id.
     */
    void deleteById(Integer id);

    /**
     * Inserts a new record.
     */
    void insert(Clazz clazz);

    /**
     * Queries a class by id.
     */
    @Select("select * from cls where id = #{id}")
    Clazz getClsById(Integer id);


    /**
     * Updates an existing record.
     */
    void update(Clazz clazz);

    /**
     * Queries all records.
     */
    @Select("select * from cls")
    List<Clazz> findAll();
}
