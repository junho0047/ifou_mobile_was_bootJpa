package com.nifou.m.ifou_mobile_was.repository.result;

import com.nifou.m.ifou_mobile_was.entity.result.Sub05Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface Sub05Repository extends JpaRepository<Sub05Entity, Long> {
    ArrayList<Sub05Entity> getSub05(String orgcd, String setWhere);
}
