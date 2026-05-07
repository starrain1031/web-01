package org.starry.webmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.starry.webmanagement.mapper.EmpMapper;
import org.starry.webmanagement.pojo.JobOption;

import java.util.List;
import java.util.Map;

@Component
public class ReportServiceImpl implements ReportService{

    @Autowired
    private EmpMapper empMapper;

    @Override
    public JobOption countEmpJobData() {
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        JobOption jobOption = new JobOption();
        jobOption.setJobList(list.stream().map(item -> item.get("pos")).toList());
        jobOption.setDataList(list.stream().map(item -> item.get("total")).toList());
        return jobOption;
    }

    @Override
    public List<Map<String, Object>> countEmpGenderData() {
        return empMapper.countEmpGenderData();
    }
}
