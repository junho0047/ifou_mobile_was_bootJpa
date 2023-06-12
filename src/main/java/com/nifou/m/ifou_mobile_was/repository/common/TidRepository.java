package com.nifou.m.ifou_mobile_was.repository.common;

import com.nifou.m.ifou_mobile_was.entity.common.DepEntity;
import com.nifou.m.ifou_mobile_was.entity.common.TidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface TidRepository extends JpaRepository<TidEntity, Long> {
    @Query(value =
            "SELECT" +
            "            TERM_NM              AS tidnm," +
            "            TERM_ID              AS tidcd" +
            "       FROM" +
            "            TB_BAS_TIDMST" +
            "       WHERE" +
            "            ORG_CD = :orgcd"
            , nativeQuery = true)
    public ArrayList<TidEntity> getTid(@Param("orgcd") String orgcd);}
