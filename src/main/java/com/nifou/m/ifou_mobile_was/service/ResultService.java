package com.nifou.m.ifou_mobile_was.service;

import com.nifou.m.ifou_mobile_was.entity.result.Sub01Entity;
import com.nifou.m.ifou_mobile_was.entity.result.Sub02Entity;
import com.nifou.m.ifou_mobile_was.entity.result.Sub03Entity;
import com.nifou.m.ifou_mobile_was.entity.result.Sub04Entity;
import com.nifou.m.ifou_mobile_was.repository.result.Sub01RepositoryImpl;
import com.nifou.m.ifou_mobile_was.repository.result.Sub02RepositoryImpl;
import com.nifou.m.ifou_mobile_was.repository.result.Sub03RepositoryImpl;
import com.nifou.m.ifou_mobile_was.repository.result.Sub04RepositoryImpl;
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
}
