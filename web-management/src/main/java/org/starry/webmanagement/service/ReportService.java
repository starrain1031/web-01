package org.starry.webmanagement.service;


import org.starry.webmanagement.pojo.ClazzCountOption;
import org.starry.webmanagement.pojo.JobOption;

import java.util.List;
import java.util.Map;

/**
 * Service contract for report and statistics operations.
 */
public interface ReportService {

    /**
     * Counts employees grouped by job type.
     */
    JobOption countEmpJobData();

    /**
     * Counts employees grouped by gender.
     */
    List<Map<String, Object>> countEmpGenderData();

    List<Map<String, Object>> getStudentDegreeData();

    ClazzCountOption getStudentCountData();
}
