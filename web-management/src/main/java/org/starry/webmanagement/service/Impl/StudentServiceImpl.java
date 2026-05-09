package org.starry.webmanagement.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.starry.webmanagement.mapper.StudentMapper;
import org.starry.webmanagement.pojo.PageResult;
import org.starry.webmanagement.pojo.Student;
import org.starry.webmanagement.service.StudentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    @Override
    public PageResult<Student> page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Student> rows = studentMapper.list(name,degree,clazzId);
        Page<Student> p = (Page<Student>) rows;
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    @Override
    public Student getInfo(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    @Override
    public void violationHandle(Integer id, Integer score) {
        studentMapper.violationHandle(id,score);
    }

//    @Override
//    public void importStudents(Integer clazzId, MultipartFile file) {
//    }
}
