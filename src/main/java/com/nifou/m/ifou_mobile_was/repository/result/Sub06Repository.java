package com.nifou.m.ifou_mobile_was.repository.result;


import com.nifou.m.ifou_mobile_was.entity.result.Sub06Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface Sub06Repository extends JpaRepository<Sub06Entity, Long> {
    ArrayList<Sub06Entity> getSub06(String orgcd, String setWhere, String expddWhere,String acqWhere);
}
