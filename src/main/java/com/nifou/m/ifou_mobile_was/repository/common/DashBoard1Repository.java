package com.nifou.m.ifou_mobile_was.repository.common;

import com.nifou.m.ifou_mobile_was.entity.common.DashBoard1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


    public interface DashBoard1Repository extends JpaRepository<DashBoard1Entity, String> {
        @Query(value =
                "SELECT" +
                "                NVL(SUM(CCA-CCC),0)                         AS ccamt" +
                "                ,NVL(SUM(CBA-CBC),0)                        AS cbamt" +
                "                ,NVL(SUM(ICA-ICC),0)                        AS icamt" +
                "                ,NVL(SUM(CCA+CBA+ICA-CCC-CBC-ICC),0)       AS sumamt" +
                "                ,SUM(CCCNT)                                 AS cccnt" +
                "                ,SUM(CBCNT)                                 AS cbcnt" +
                "                ,SUM(ICCNT)                                 AS iccnt" +
                "                ,NVL(SUM(CCCNT+CBCNT+ICCNT),0)              AS sumcnt" +
                "                FROM" +
                "                        (SELECT" +
                "                                SUM(CASE WHEN SVCGB = 'CC' AND APPGB = 'A' THEN AMOUNT ELSE 0 END)      AS cca" +
                "                                ,SUM(CASE WHEN SVCGB = 'CC' AND APPGB = 'C' THEN AMOUNT ELSE 0 END)    AS ccc" +
                "                                ,SUM(CASE WHEN SVCGB = 'CB' AND APPGB = 'A' THEN AMOUNT ELSE 0 END)    AS cba" +
                "                                ,SUM(CASE WHEN SVCGB = 'CB' AND APPGB = 'C' THEN AMOUNT ELSE 0 END)    AS cbc" +
                "                                ,SUM(CASE WHEN SVCGB = 'IC' AND APPGB = 'A' THEN AMOUNT ELSE 0 END)    AS ica" +
                "                                ,SUM(CASE WHEN SVCGB = 'IC' AND APPGB = 'C' THEN AMOUNT ELSE 0 END)    AS icc" +
                "                                ,COUNT(CASE WHEN SVCGB = 'CC' THEN 1 END)                              AS cccnt" +
                "                                ,COUNT(CASE WHEN SVCGB = 'CB' THEN 1 END)                              AS cbcnt" +
                "                                ,COUNT(CASE WHEN SVCGB = 'IC' THEN 1 END)                              AS iccnt" +
                "                                FROM" +
                "                                GLOB_MNG_ICVAN" +
                "                                WHERE" +
                "                                TID IN (" +
                "                                        SELECT TID FROM TB_BAS_TIDMAP" +
                "                                        WHERE" +
                "                                        ORG_CD  = :orgcd)" +
                "       AND (LENGTH(:appdd) < 8 AND SUBSTR(APPDD, 0, 6) = :appdd OR APPDD = :appdd)  " +
                "           )"
                , nativeQuery = true)
        public DashBoard1Entity getDashBoard(@Param("orgcd") String orgcd,@Param("appdd") String appdd);
}
