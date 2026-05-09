package org.starry.webmanagement.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id;
    private String name;
    private String no;
    private Integer gender;// 1 male, 2 female
    private String phone;
    private String idCard;
    private Integer isCollege; // from college 1 Yes, 0 No
    private String address;
    private Integer degree; //1 Middle school, 2 High school, 3 Graduate, 4 Bachelor, 5 Master, 6 PhD
    private LocalDate graduationDate;
    private Integer clazzId;
    private Short violationCount;
    private Short violationScore;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private String clazzName;
}
