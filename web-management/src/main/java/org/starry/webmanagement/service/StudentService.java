package org.starry.webmanagement.service;

import org.springframework.web.multipart.MultipartFile;
import org.starry.webmanagement.pojo.PageResult;
import org.starry.webmanagement.pojo.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    void save(Student student);

    PageResult<Student> page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize);

    Student getInfo(Integer id);

    void update(Student student);

    void delete(List<Integer> ids);

    void violationHandle(Integer id, Integer score);

//    void importStudents(Integer clazzId, MultipartFile file);
}
