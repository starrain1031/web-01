package org.starry.webmanagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.starry.webmanagement.pojo.Clazz;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ClazzMapper {

    List<Clazz> list(String name, LocalDate begin, LocalDate end);

    void deleteById(Integer id);

    void insert(Clazz clazz);

    @Select("select * from cls where id = #{id}")
    Clazz getClsById(Integer id);


    void update(Clazz clazz);

    @Select("select * from cls")
    List<Clazz> findAll();
}
