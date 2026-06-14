package org.starry.webmanagement.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.starry.webmanagement.pojo.PageResult;
import org.starry.webmanagement.pojo.Result;
import org.starry.webmanagement.pojo.Student;
import org.starry.webmanagement.service.StudentService;

import java.util.List;

/**
 * REST controller for student management APIs.
 */
@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * Creates a new student record.
     */
    @PostMapping
    public Result save(@RequestBody Student student){
        studentService.save(student);
        return Result.success();
    }

//    @PostMapping("/import/{clazzId}")
//    public Result importStudents(@PathVariable Integer clazzId, MultipartFile file) {
//        studentService.importStudents(clazzId, file);
//        return Result.success();
//    }

    /**
     * Queries students with pagination and optional filters.
     */
    @GetMapping
    public Result page(String name ,
                       Integer degree,
                       Integer clazzId,
                       @RequestParam(defaultValue = "1") Integer page ,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        PageResult<Student> pageResult = studentService.page(name,degree,clazzId,page,pageSize);
        return Result.success(pageResult);
    }

    /**
     * Queries a student by id.
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }

    /**
     * Updates an existing student record.
     */
    @PutMapping
    public Result update(@RequestBody Student student){
        studentService.update(student);
        return Result.success();
    }

    /**
     * Deletes students by id list.
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        studentService.delete(ids);
        return Result.success();
    }

    /**
     * Records a student violation and adds the violation score.
     */
    @PutMapping("/violation/{id}/{score}")
    public Result violationHandle(@PathVariable Integer id , @PathVariable Integer score){
        if (score == null || score <= 0) {
            return Result.error("score must be greater than 0");
        }
        studentService.violationHandle(id, score);
        return Result.success();
    }
}