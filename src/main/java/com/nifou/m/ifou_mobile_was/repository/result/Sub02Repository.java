package com.nifou.m.ifou_mobile_was.repository.result;

import com.nifou.m.ifou_mobile_was.entity.result.Sub01Entity;
import com.nifou.m.ifou_mobile_was.entity.result.Sub02Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface Sub02Repository extends JpaRepository<Sub02Entity, Long> {
    ArrayList<Sub02Entity> getSub02(String orgcd, String setWhere);
}
