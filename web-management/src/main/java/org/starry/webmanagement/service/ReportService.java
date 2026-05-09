package org.starry.webmanagement.service;


import org.starry.webmanagement.pojo.ClazzCountOption;
import org.starry.webmanagement.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {

    JobOption countEmpJobData();

    List<Map<String, Object>> countEmpGenderData();

    List<Map<String, Object>> getStudentDegreeData();

    ClazzCountOption getStudentCountData();
}
