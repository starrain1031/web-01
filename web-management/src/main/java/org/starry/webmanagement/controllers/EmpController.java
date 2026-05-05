package org.starry.webmanagement.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.starry.webmanagement.pojo.Emp;
import org.starry.webmanagement.pojo.EmpQueryParam;
import org.starry.webmanagement.pojo.PageResult;
import org.starry.webmanagement.pojo.Result;
import org.starry.webmanagement.service.EmpService;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(EmpQueryParam  empQueryParam) {
        log.info("page query: {}", empQueryParam);
        PageResult<Emp> pageResult= empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("save emp: {}", emp);
        return empService.save(emp) ? Result.success() : Result.error("Failed to add" + emp.getName());
    }
}
