package com.nifou.m.ifou_mobile_was.service;

import com.nifou.m.ifou_mobile_was.entity.result.Sub01Entity;
import com.nifou.m.ifou_mobile_was.repository.result.Sub01Repository;
import com.nifou.m.ifou_mobile_was.repository.result.Sub01RepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class ResultService {
    @Autowired
    private Sub01RepositoryImpl sub01Repository;


    public ArrayList<Sub01Entity> getSub01(String orgcd, String setWhere) {
        ArrayList<Sub01Entity> sub01 = sub01Repository.getSub01(orgcd, setWhere);
        return sub01;
    }
}
