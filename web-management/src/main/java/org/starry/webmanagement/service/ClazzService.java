package org.starry.webmanagement.service;

import org.starry.webmanagement.pojo.Clazz;
import org.starry.webmanagement.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

/**
 * Service contract for class management operations.
 */
public interface ClazzService {


    /**
     * Queries records with pagination and filters.
     */
    PageResult<Clazz> page(String name, LocalDate begin, LocalDate end, int page, int pageSize);

    /**
     * Deletes a record by id.
     */
    void deleteById(Integer id);

    /**
     * Creates a new record.
     */
    void save(Clazz clazz);

    /**
     * Queries a class by id.
     */
    Clazz getclazzById(Integer id);

    /**
     * Updates an existing record.
     */
    void update(Clazz clazz);

    /**
     * Queries all records.
     */
    List<Clazz> findAll();
}
