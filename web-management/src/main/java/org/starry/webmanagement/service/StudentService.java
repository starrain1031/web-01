package org.starry.webmanagement.service;

import org.starry.webmanagement.pojo.PageResult;
import org.starry.webmanagement.pojo.Student;

import java.util.List;

/**
 * Service contract for student management operations.
 */
public interface StudentService {
    /**
     * Creates a new record.
     */
    void save(Student student);

    /**
     * Queries records with pagination and filters.
     */
    PageResult<Student> page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize);

    /**
     * Queries detailed record information by id.
     */
    Student getInfo(Integer id);

    /**
     * Updates an existing record.
     */
    void update(Student student);

    /**
     * Deletes records by id list.
     */
    void delete(List<Integer> ids);

    /**
     * Updates student violation count and score.
     */
    void violationHandle(Integer id, Integer score);

//    void importStudents(Integer clazzId, MultipartFile file);
}
