package com.nifou.m.ifou_mobile_was.repository.common;

import com.nifou.m.ifou_mobile_was.entity.common.DepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface DepRepository extends JpaRepository<DepEntity, Long> {
    @Query(value =
            "SELECT" +
            "            DEP_CD              AS depcd," +
            "            DEP_NM              AS depnm" +
            "      FROM" +
            "            TB_BAS_DEPART" +
            "      WHERE" +
            "            ORG_CD = :orgcd"
            , nativeQuery = true)
    public ArrayList<DepEntity> getDep(@Param("orgcd") String orgcd);}
