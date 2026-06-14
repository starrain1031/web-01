package org.starry.webmanagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.starry.webmanagement.pojo.EmpExpr;

import java.util.List;

/**
 * MyBatis mapper for employee work experience persistence operations.
 */
@Mapper
public interface EmpExprMapper {

    /**
     * Inserts records in batch.
     */
    void insertBatch(List<EmpExpr> exprList);

    /**
     * Deletes employee work experiences by employee id list.
     */
    void deleteByEmpIds(List<Integer> empIds);
}
