package com.nifou.m.ifou_mobile_was.repository.result;

import com.nifou.m.ifou_mobile_was.entity.result.Sub04Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface Sub04Repository extends JpaRepository<Sub04Entity, Long> {

    ArrayList<Sub04Entity> getSub04(String orgcd, String setWhere);
}
