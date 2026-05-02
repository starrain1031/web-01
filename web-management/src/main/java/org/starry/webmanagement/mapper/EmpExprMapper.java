package org.starry.webmanagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.starry.webmanagement.pojo.EmpExpr;

import java.util.List;

@Mapper
public interface EmpExprMapper {


    void insertBatch(List<EmpExpr> exprList);
}
