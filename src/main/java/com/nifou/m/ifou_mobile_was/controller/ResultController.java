package com.nifou.m.ifou_mobile_was.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nifou.m.ifou_mobile_was.entity.result.Sub01Entity;
import com.nifou.m.ifou_mobile_was.entity.result.WhereEntity;
import com.nifou.m.ifou_mobile_was.service.ResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@RestController
@RequestMapping("")
public class ResultController {
    @Autowired
    private ResultService resultService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "sub01", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTbSysDomain(WhereEntity whereEntity,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response) throws NoSuchAlgorithmException, JsonProcessingException, IOException, JSONException {
        // 카드사별 조회 API

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Accept", "application/x-www-form-urlencoded");
        response.setHeader("Access-Control-Allow-Origin", "*");

        // 파라미터 설정
        String sappdd = whereEntity.getSappdd();
        String eappdd = whereEntity.getEappdd();
        String orgcd  = whereEntity.getOrgcd();
        String depcd  = whereEntity.getDepcd();
        String acqcd  = whereEntity.getAcqcd();

        String setWhere = "";

        // 날짜 조건검색
        setWhere += " AND APPDD BETWEEN '"+sappdd+"' AND '"+eappdd+"'";

        // 사업부 조건 검색
        if(depcd==null||depcd.equals("")) {
            setWhere += " AND TID IN (SELECT TID FROM TB_BAS_TIDMAP  WHERE ORG_CD='"+orgcd+"') ";
        } else {
            setWhere += " AND TID IN (SELECT TID FROM TB_BAS_TIDMAP  WHERE DEP_CD='"+depcd+"' AND ORG_CD='"+orgcd+"') ";
        }

        // 카드사 조건 검색
        if(acqcd==null||acqcd.equals("")) {
            setWhere += "";
        } else {
            setWhere += " AND ACQ_CD IN('"+acqcd+"')";
        }

        ArrayList<Sub01Entity> sub01 = resultService.getSub01(orgcd, setWhere);

        // ColumnsInfo에 저장된 데이터 뽑아서 json형식으로 변환
        JSONObject jsonOb = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Sub01Entity i : sub01) {

            jsonOb.put("appdd", i.getAppdd());
            jsonOb.put("dep", i.getDepnm());
            jsonOb.put("card", i.getAcqnm());
//            jsonOb.put("ACQCD", i.getAcqcd());
            jsonOb.put("cnt", i.getTotcnt());
            jsonOb.put("amt", i.getTotamt());

            jsonArray.put(jsonOb);
            jsonOb = new JSONObject();
        }
        return ResponseEntity.ok(jsonArray.toString());
    }
}