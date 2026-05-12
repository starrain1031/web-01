package org.starry.webmanagement.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.starry.webmanagement.pojo.OperateLog;
import org.starry.webmanagement.pojo.PageResult;
import org.starry.webmanagement.pojo.Result;
import org.starry.webmanagement.service.LogService;


@Slf4j
@RequestMapping("/log")
@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int pageSize) {
        log.info("page query");
        PageResult<OperateLog> pageResult = logService.page(page, pageSize);
        return Result.success(pageResult);
    }
}
