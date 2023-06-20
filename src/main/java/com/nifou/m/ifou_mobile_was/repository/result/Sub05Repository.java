package com.nifou.m.ifou_mobile_was.repository.result;

import com.nifou.m.ifou_mobile_was.entity.result.Sub05Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface Sub05Repository extends JpaRepository<Sub05Entity, Long> {
    ArrayList<Sub05Entity> getSub05(String orgcd, String setWhere);
}
