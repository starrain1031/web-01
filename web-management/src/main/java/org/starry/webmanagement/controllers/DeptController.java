package org.starry.webmanagement.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.starry.webmanagement.pojo.Dept;
import org.starry.webmanagement.pojo.Result;
import org.starry.webmanagement.service.DeptService;

import java.util.List;

@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;
//    @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping
    public Result list() {
        System.out.println("query all departments");
        List<Dept> deptList= deptService.findAll();
        return Result.success(deptList);
    }

    @DeleteMapping
    public Result delete(@RequestParam("id") Integer deptId) {
        deptService.deleteById(deptId);
        System.out.println("delete department " + deptId);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Dept dept){
        System.out.println("add department " + dept);
        return deptService.add(dept) ? Result.success() : Result.error("Failed to add" + dept.getName());
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable("id") Integer deptId) {
        System.out.println("query department " + deptId);
        Dept dept = deptService.getDeptById(deptId);
        return dept != null ? Result.success(dept) : Result.error("No department " + deptId);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        System.out.println("update department " + dept);
        return deptService.update(dept) ? Result.success() : Result.error("Failed to update" + dept.getName());
    }
}
