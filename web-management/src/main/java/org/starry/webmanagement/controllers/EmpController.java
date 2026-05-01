package org.starry.webmanagement.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.starry.webmanagement.pojo.Emp;
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
    public Result page(@RequestParam(defaultValue = "1")Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize) {
        log.info("page query: {} {}", page, pageSize);
        PageResult<Emp> pageResult= empService.page(page,pageSize);
        return Result.success(pageResult);
    }
}
