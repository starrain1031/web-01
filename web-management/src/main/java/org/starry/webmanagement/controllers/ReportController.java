package org.starry.webmanagement.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.starry.webmanagement.pojo.JobOption;
import org.starry.webmanagement.pojo.Result;
import org.starry.webmanagement.service.ReportService;


/**
 * REST controller for report and statistics APIs.
 */
@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/empJobData")
    public Result reportEmpJob() {
        log.info("report emp job data");
        JobOption jobOption = reportService.countEmpJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result reportEmpGender() {
        log.info("report emp gender data");
        return Result.success(reportService.countEmpGenderData());
    }
    @GetMapping("/studentDegreeData")
    public Result reportStudentDegree() {
        log.info("report student degree data");
        return Result.success(reportService.getStudentDegreeData());
    }
    @GetMapping("/studentCountData")
    public Result reportStudentCount() {
        log.info("report student count data");
        return Result.success(reportService.getStudentCountData());
    }
}
