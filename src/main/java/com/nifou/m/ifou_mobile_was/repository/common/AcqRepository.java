package com.nifou.m.ifou_mobile_was.repository.common;

import com.nifou.m.ifou_mobile_was.entity.common.AcqEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;


public interface AcqRepository extends JpaRepository<AcqEntity, Long> {
    @Query(value =
            "SELECT" +
            "            PUR_CD              AS purcd," +
            "            PUR_NM              AS purnm," +
            "            PUR_KOCES           AS purkoces" +
            "       FROM" +
            "            TB_BAS_PURINFO" +
            "       WHERE" +
            "            PUR_USE='Y'" +
            "       ORDER BY" +
            "            PUR_NM" +
            "       ASC"
            , nativeQuery = true)
    public ArrayList<AcqEntity> getAcq();}
