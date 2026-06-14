package org.starry.webmanagement.pojo;

import lombok.Data;

import java.time.LocalDate;

/**
 * working experience
 */
/**
 * Employee work experience entity.
 */
@Data
public class EmpExpr {
    private Integer id;
    private Integer empId;
    private LocalDate begin;
    private LocalDate end;
    private String company;
    private String job;
}
