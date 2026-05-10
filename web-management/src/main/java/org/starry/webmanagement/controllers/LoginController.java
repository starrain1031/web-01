package org.starry.webmanagement.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.starry.webmanagement.pojo.Emp;
import org.starry.webmanagement.pojo.LoginInfo;
import org.starry.webmanagement.pojo.Result;
import org.starry.webmanagement.service.EmpService;

@Slf4j
@RestController
public class LoginController {
    private final EmpService empService;

    public LoginController(EmpService empService) {
        this.empService = empService;
    }

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("Login: {}", emp);
        LoginInfo info = empService.login(emp);
        if (info != null){
            return Result.success(info);
        }
        return Result.error("Login failed");
    }
}
