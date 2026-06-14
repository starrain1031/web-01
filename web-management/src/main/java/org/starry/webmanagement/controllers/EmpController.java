package org.starry.webmanagement.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.starry.webmanagement.pojo.*;
import org.starry.webmanagement.service.EmpService;

import java.util.List;

/**
 * REST controller for employee management APIs.
 */
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * Queries employees with pagination and optional filters.
     */
    @GetMapping
    public Result page(EmpQueryParam  empQueryParam) {
        log.info("page query: {}", empQueryParam);
        PageResult<Emp> pageResult= empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * Creates a new employee.
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("save emp: {}", emp);
        return empService.save(emp) ? Result.success() : Result.error("Failed to add" + emp.getName());
    }

    /**
     * Deletes employees by id list.
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("delete ids: {}", ids);
        return empService.delete(ids) ? Result.success() : Result.error("Failed to delete");
    }

    /**
     * Queries an employee by id.
     */
    @GetMapping("/{id}")
    public Result get(@PathVariable("id") Integer empId) {
        log.info("query employee {}" ,empId);
        Emp emp = empService.getEmpById(empId);
        return emp != null ? Result.success(emp) : Result.error("No employee " + empId);
    }

    /**
     * Updates an existing employee.
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("update department {}", emp);
        return empService.update(emp) ? Result.success() : Result.error("Failed to update" + emp.getName());
    }

    /**
     * Queries all employees.
     */
    @GetMapping("/list")
    public Result list() {
        log.info("query all employees");
        List<Emp> empList= empService.findAll();
        return Result.success(empList);
    }
}
