package com.nifou.m.ifou_mobile_was.service;

import com.nifou.m.ifou_mobile_was.entity.result.*;
import com.nifou.m.ifou_mobile_was.repository.result.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class ResultService {
    @Autowired
    private Sub01RepositoryImpl sub01Repository;
    @Autowired
    private Sub02RepositoryImpl sub02Repository;

    @Autowired
    private Sub03RepositoryImpl sub03Repository;
    @Autowired
    private Sub04RepositoryImpl sub04Repository;
    @Autowired
    private Sub05RepositoryImpl sub05Repository;
    public ArrayList<Sub01Entity> getSub01(String orgcd, String setWhere) {
        ArrayList<Sub01Entity> sub01 = sub01Repository.getSub01(orgcd, setWhere);
        return sub01;
    }

    public ArrayList<Sub02Entity> getSub02(String orgcd, String setWhere) {
        ArrayList<Sub02Entity> sub02 = sub02Repository.getSub02(orgcd, setWhere);
        return sub02;
    }

    public ArrayList<Sub03Entity> getSub03(String orgcd, String setWhere) {
        ArrayList<Sub03Entity> sub03 = sub03Repository.getSub03(orgcd, setWhere);
        return sub03;
    }

    public ArrayList<Sub04Entity> getSub04(String orgcd, String setWhere) {
        ArrayList<Sub04Entity> sub04 = sub04Repository.getSub04(orgcd, setWhere);
        return sub04;
    }

    public ArrayList<Sub05Entity> getSub05(String orgcd, String setWhere, String setWhere2) {
        ArrayList<Sub05Entity> sub05 = sub05Repository.getSub05(orgcd, setWhere, setWhere2);
        return sub05;
    }
}
