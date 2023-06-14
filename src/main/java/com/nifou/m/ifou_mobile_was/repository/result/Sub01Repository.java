package com.nifou.m.ifou_mobile_was.repository.result;

import com.nifou.m.ifou_mobile_was.entity.result.Sub01Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface Sub01Repository extends JpaRepository<Sub01Entity, Long> {
    ArrayList<Sub01Entity> getSub01(String orgcd, String setWhere);
}
