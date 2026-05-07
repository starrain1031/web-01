package org.starry.webmanagement.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.starry.webmanagement.pojo.Result;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error(e.getMessage());
        return Result.error("Service not available");
    }

    @ExceptionHandler
    public Result handleDuplicationKeyException(DuplicateKeyException e) {
        log.error(e.getMessage());
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] split = errMsg.split(" ");
        String msg = split[2] + "already exists";
        return Result.error(msg);
    }
}
