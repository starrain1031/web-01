package org.starry.webmanagement.service;

import org.starry.webmanagement.pojo.Clazz;
import org.starry.webmanagement.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface ClazzService {


    PageResult<Clazz> page(String name, LocalDate begin, LocalDate end, int page, int pageSize);

    void deleteById(Integer id);

    void save(Clazz clazz);

    Clazz getclazzById(Integer id);

    void update(Clazz clazz);

    List<Clazz> findAll();
}
