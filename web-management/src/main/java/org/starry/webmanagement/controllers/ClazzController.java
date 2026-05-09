package org.starry.webmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.starry.webmanagement.pojo.Clazz;
import org.starry.webmanagement.pojo.PageResult;
import org.starry.webmanagement.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.starry.webmanagement.service.ClazzService;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result page(String name,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                       @RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int pageSize) {
        log.info("page query {}", name);
        PageResult<Clazz> pageResult = clazzService.page(name, begin, end, page, pageSize);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Clazz clazz) {
        log.info("Add class: {}", clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getClazzById(@PathVariable Integer id) {
        log.info("Search class by id: {}", id);
        Clazz clazz = clazzService.getclazzById(id);
        return Result.success(clazz);
    }

    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("Update class: {}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("Delete Class: {}", id);
        clazzService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list() {
        log.info("Query all classes");
        List<Clazz> clsList= clazzService.findAll();
        return Result.success(clsList);
    }
}
