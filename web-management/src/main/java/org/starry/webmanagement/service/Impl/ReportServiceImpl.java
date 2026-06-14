package org.starry.webmanagement.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.starry.webmanagement.mapper.EmpMapper;
import org.starry.webmanagement.mapper.StudentMapper;
import org.starry.webmanagement.pojo.ClazzCountOption;
import org.starry.webmanagement.pojo.JobOption;
import org.starry.webmanagement.service.ReportService;

import java.util.List;
import java.util.Map;

/**
 * Default implementation of report and statistics business logic.
 */
@Component
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * Counts employees grouped by job type.
     */
    @Override
    public JobOption countEmpJobData() {
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        JobOption jobOption = new JobOption();
        jobOption.setJobList(list.stream().map(item -> item.get("pos")).toList());
        jobOption.setDataList(list.stream().map(item -> item.get("total")).toList());
        return jobOption;
    }

    /**
     * Counts employees grouped by gender.
     */
    @Override
    public List<Map<String, Object>> countEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentMapper.countStudentDegreeData();
    }

    @Override
    public ClazzCountOption getStudentCountData() {
        List<Map<String, Object>> countList = studentMapper.getStudentCount();
        if(!CollectionUtils.isEmpty(countList)){
            List<Object> clazzList = countList.stream().map(map -> {
                return map.get("cname");
            }).toList();

            List<Object> dataList = countList.stream().map(map -> {
                return map.get("scount");
            }).toList();

            return new ClazzCountOption(clazzList, dataList);
        }
        return null;
    }
}
