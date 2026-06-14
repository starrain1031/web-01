package org.starry.webmanagement.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Employee entity used by employee management and authentication APIs.
 */
@Data
public class Emp {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer gender;
    private String phone;
    private Integer job;//1 homeroom teacher, 2 lecturer, 3 student affairs supervisor, 4 teaching research supervisor, 5 consultant
    private Integer salary;
    private String image;
    private LocalDate entryDate;
    private Integer deptId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private String deptName;
    private List<EmpExpr> exprList;
}
