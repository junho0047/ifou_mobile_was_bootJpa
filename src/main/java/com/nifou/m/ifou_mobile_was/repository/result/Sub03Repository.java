package com.nifou.m.ifou_mobile_was.repository.result;

import com.nifou.m.ifou_mobile_was.entity.result.Sub03Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface Sub03Repository extends JpaRepository<Sub03Entity, Long> {
    ArrayList<Sub03Entity> getSub03(String orgcd, String setWhere);
}
