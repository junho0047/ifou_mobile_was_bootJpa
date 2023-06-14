package com.nifou.m.ifou_mobile_was.repository.common;

import com.nifou.m.ifou_mobile_was.entity.common.ColumnsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface ColumnsRepository extends JpaRepository<ColumnsEntity, Long> {
    @Query(value =
            "SELECT" +
            "            FIELDS_TXT              AS fieldstxt," +
            "            ALIGNS                  AS aligns   ," +
            "            COL_TYPE                AS coltype  ," +
            "            SORTS                   AS sorts    ," +
            "            WIDTHS                  AS widths   ," +
            "            POS_FIELD               AS posfield  ," +
            "            VAN_FIELD               AS vanfield ," +
            "            CASH_FIELD              AS cashfield," +
            "            PAGES                   AS pages    ," +
            "            ORN                     AS orn      ," +
            "            ROWSPAN                 AS rowspan  ," +
            "            COLSPAN                 AS colspan  ," +
            "            COL_CHK                 AS colchk   ," +
            "            COL_TXT                 AS coltxt" +
            "      FROM" +
            "            TB_SYS_DOMAIN" +
            "      WHERE" +
            "                    ORGCD=:orgcd" +
            "      AND" +
            "            pages = :pages" +
            "      ORDER BY orn ASC"
                , nativeQuery = true)
    public ArrayList<ColumnsEntity> getTbSysDomain(@Param("orgcd") String orgcd, @Param("pages") String pages);}