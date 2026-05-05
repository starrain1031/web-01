package org.starry.webmanagement.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.starry.webmanagement.pojo.Dept;
import org.starry.webmanagement.pojo.Result;
import org.starry.webmanagement.service.DeptService;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

//    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;
//    @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping
    public Result list() {
        log.info("query all departments");
        List<Dept> deptList= deptService.findAll();
        return Result.success(deptList);
    }

    @DeleteMapping
    public Result delete(@RequestParam("id") Integer deptId) {
        deptService.deleteById(deptId);
        log.info("delete department {} ", deptId);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("add department {}", dept);
        return deptService.add(dept) ? Result.success() : Result.error("Failed to add" + dept.getName());
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable("id") Integer deptId) {
        log.info("query department {}" ,deptId);
        Dept dept = deptService.getDeptById(deptId);
        return dept != null ? Result.success(dept) : Result.error("No department " + deptId);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("update department {}", dept);
        return deptService.update(dept) ? Result.success() : Result.error("Failed to update" + dept.getName());
    }
}
