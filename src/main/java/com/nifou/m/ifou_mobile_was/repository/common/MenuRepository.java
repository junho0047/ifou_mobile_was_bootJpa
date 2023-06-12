package com.nifou.m.ifou_mobile_was.repository.common;

import com.nifou.m.ifou_mobile_was.entity.common.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    @Query(value =
            "SELECT" +
            "            A.PROGRAM_SEQ       AS programseq," +
            "            B.PROGRAM_NAME      AS menuname," +
            "            B.DEPTH             AS menudepth," +
            "            B.PARENT_SEQ        AS parentseq," +
            "            A.ENABLE_READ       AS authr," +
            "            A.ENABLE_CREATE     AS authc," +
            "            A.ENABLE_UPDATE     AS authu," +
            "            A.ENABLE_DELETE     AS authd," +
            "            B.SRC_LOCATION      AS murl" +
            "   FROM TB_SYS_MENU A" +
            "       LEFT OUTER JOIN" +
            "       (SELECT PROGRAM_SEQ, PROGRAM_NAME, PARENT_SEQ, DEPTH, SRC_LOCATION, SORT FROM TB_SYS_PROGRAM) B" +
            "       ON (A.PROGRAM_SEQ=B.PROGRAM_SEQ)" +
            "   WHERE A.AUTH_SEQ = :authseq AND ORGCD = :orgcd  AND ENABLE_READ ='M'" +
            "       ORDER BY B.SORT ASC"
            , nativeQuery = true)
    public ArrayList<MenuEntity> getMenu(@Param("orgcd") String orgcd, @Param("authseq") String authseq);}
