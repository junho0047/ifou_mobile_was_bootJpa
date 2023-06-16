package com.nifou.m.ifou_mobile_was.repository.common;

import com.nifou.m.ifou_mobile_was.entity.common.DashBoard1Entity;
import com.nifou.m.ifou_mobile_was.entity.common.DashBoard2Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DashBoard2Repository extends JpaRepository<DashBoard2Entity, String> {
    @Query(value =
            "SELECT" +
            "            NVL(SUM(SALE_AMT),0)           AS saleamt" +
            "            ,NVL(SUM(FEE),0)               AS fee" +
            "            ,NVL(SUM(SALE_AMT-FEE),0)      AS sum" +
            "    FROM" +
            "            TB_MNG_DEPDATA" +
            "    WHERE" +
            "    TID IN (SELECT TID FROM TB_BAS_TIDMAP WHERE ORG_CD =  :orgcd)" +
            "    AND EXP_DD = :expdd"
            , nativeQuery = true)
    public DashBoard2Entity getDashBoard2(@Param("orgcd") String orgcd, @Param("expdd") String expdd);}
