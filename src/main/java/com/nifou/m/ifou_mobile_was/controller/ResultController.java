package com.nifou.m.ifou_mobile_was.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nifou.m.ifou_mobile_was.entity.result.*;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("")
public class ResultController {
    @Autowired
    private ResultService resultService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "sub01", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getSub01(WhereEntity whereEntity,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response) throws NoSuchAlgorithmException, JsonProcessingException, IOException, JSONException, ParseException {
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

        // sub01에 저장된 데이터 뽑아서 json형식으로 변환
        JSONObject jsonOb = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Sub01Entity i : sub01) {


            // 날짜형식 변환
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyMMdd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yy.MM.dd");
            Date beforeSappdd = inputFormat.parse(i.getAppdd().substring(2,8));
            String AfterSappdd = outputFormat.format(beforeSappdd);



            jsonOb.put("appdd", AfterSappdd);
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

    @PostMapping(value = "sub02", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getSub02(WhereEntity whereEntity,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response) throws NoSuchAlgorithmException, JsonProcessingException, IOException, JSONException, ParseException {
        // 단말기별 조회 API

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Accept", "application/x-www-form-urlencoded");
        response.setHeader("Access-Control-Allow-Origin", "*");

        // 파라미터 설정
        String sappdd = whereEntity.getSappdd();
        String eappdd = whereEntity.getEappdd();
        String orgcd  = whereEntity.getOrgcd();
        String depcd  = whereEntity.getDepcd();
        String tid  = whereEntity.getTid();




        String setWhere = "";

        // 날짜 조건검색
        setWhere += " AND APPDD BETWEEN '"+sappdd+"' AND '"+eappdd+"'";

        // 사업부 조건 검색
        if(depcd==null||depcd.equals("")) {
            setWhere += " AND TID IN (SELECT TID FROM TB_BAS_TIDMAP  WHERE ORG_CD='"+orgcd+"') ";
        } else {
            setWhere += " AND TID IN (SELECT TID FROM TB_BAS_TIDMAP  WHERE DEP_CD='"+depcd+"' AND ORG_CD='"+orgcd+"') ";
        }

        // 단말기 조건 검색
        if(tid==null||tid.equals("")) {
            setWhere += "";
        } else {
            setWhere += " AND TID IN('"+tid+"')";
        }

        ArrayList<Sub02Entity> sub02 = resultService.getSub02(orgcd, setWhere);

        // sub02에 저장된 데이터 뽑아서 json형식으로 변환
        JSONObject jsonOb = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Sub02Entity i : sub02) {


            // 날짜형식 변환
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyMMdd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yy.MM.dd");
            Date beforeSappdd = inputFormat.parse(i.getAppdd().substring(2,8));
            String AfterSappdd = outputFormat.format(beforeSappdd);



            jsonOb.put("appdd", AfterSappdd);
            jsonOb.put("dep", i.getDepnm());
            jsonOb.put("tid", i.getTid());
            jsonOb.put("tidnm", i.getTidnm());
            jsonOb.put("cnt", i.getTotcnt());
            jsonOb.put("amt", i.getTotamt());

            jsonArray.put(jsonOb);
            jsonOb = new JSONObject();
        }
        return ResponseEntity.ok(jsonArray.toString());
    }

    @PostMapping(value = "sub03", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getSub03(WhereEntity whereEntity,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws NoSuchAlgorithmException, JsonProcessingException, IOException, JSONException, ParseException {
        // 현금영수증 조회 API

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Accept", "application/x-www-form-urlencoded");
        response.setHeader("Access-Control-Allow-Origin", "*");

        // 파라미터 설정
        String sappdd = whereEntity.getSappdd();
        String eappdd = whereEntity.getEappdd();
        String orgcd  = whereEntity.getOrgcd();
        String depcd  = whereEntity.getDepcd();
        String tid  = whereEntity.getTid();




        String setWhere = "";

        // 날짜 조건검색
        setWhere += " AND APPDD BETWEEN '"+sappdd+"' AND '"+eappdd+"'";

        // 사업부 조건 검색
        if(depcd==null||depcd.equals("")) {
            setWhere += " AND TID IN (SELECT TID FROM TB_BAS_TIDMAP  WHERE ORG_CD='"+orgcd+"') ";
        } else {
            setWhere += " AND TID IN (SELECT TID FROM TB_BAS_TIDMAP  WHERE DEP_CD='"+depcd+"' AND ORG_CD='"+orgcd+"') ";
        }

        // 단말기 조건 검색
        if(tid==null||tid.equals("")) {
            setWhere += "";
        } else {
            setWhere += " AND TID IN('"+tid+"')";
        }

        ArrayList<Sub03Entity> sub03 = resultService.getSub03(orgcd, setWhere);

        // sub03에 저장된 데이터 뽑아서 json형식으로 변환
        JSONObject jsonOb = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Sub03Entity i : sub03) {


            // 날짜형식 변환
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyMMdd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yy.MM.dd");
            Date beforeSappdd = inputFormat.parse(i.getAppdd().substring(2,8));
            String AfterSappdd = outputFormat.format(beforeSappdd);



            jsonOb.put("appdd", AfterSappdd);
            jsonOb.put("dep", i.getDepnm());
            jsonOb.put("tid", i.getTid());
            jsonOb.put("tidnm", i.getTidnm());
            jsonOb.put("cnt", i.getTotcnt());
            jsonOb.put("amt", i.getTotamt());

            jsonArray.put(jsonOb);
            jsonOb = new JSONObject();
        }
        return ResponseEntity.ok(jsonArray.toString());
    }

    @PostMapping(value = "sub04", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getSub04(WhereEntity whereEntity,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws NoSuchAlgorithmException, JsonProcessingException, IOException, JSONException, ParseException {
        // 현금영수증 조회 API

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Accept", "application/x-www-form-urlencoded");
        response.setHeader("Access-Control-Allow-Origin", "*");

        // 파라미터 설정
        String sappdd = whereEntity.getSappdd();
        String eappdd = whereEntity.getEappdd();
        String orgcd  = whereEntity.getOrgcd();
        String depcd  = whereEntity.getDepcd();
        String tid  = whereEntity.getTid();




        String setWhere = "";

        // 날짜 조건검색
        setWhere += " AND APPDD BETWEEN '"+sappdd+"' AND '"+eappdd+"'";

        // 사업부 조건 검색
        if(depcd==null||depcd.equals("")) {
            setWhere += " AND TID IN (SELECT TID FROM TB_BAS_TIDMAP  WHERE ORG_CD='"+orgcd+"') ";
        } else {
            setWhere += " AND TID IN (SELECT TID FROM TB_BAS_TIDMAP  WHERE DEP_CD='"+depcd+"' AND ORG_CD='"+orgcd+"') ";
        }

        // 단말기 조건 검색
        if(tid==null||tid.equals("")) {
            setWhere += "";
        } else {
            setWhere += " AND TID IN('"+tid+"')";
        }

        ArrayList<Sub04Entity> sub04 = resultService.getSub04(orgcd, setWhere);

        // sub04에 저장된 데이터 뽑아서 json형식으로 변환
        JSONObject jsonOb = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Sub04Entity i : sub04) {


            // 날짜형식 변환
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyMMdd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yy.MM.dd");
            Date beforeSappdd = inputFormat.parse(i.getAppdd().substring(2,8));
            String AfterSappdd = outputFormat.format(beforeSappdd);



            jsonOb.put("appdd", AfterSappdd);
            jsonOb.put("dep", i.getDepnm());
            jsonOb.put("tid", i.getTid());
            jsonOb.put("tidnm", i.getTidnm());
            jsonOb.put("cnt", i.getTotcnt());
            jsonOb.put("amt", i.getTotamt());

            jsonArray.put(jsonOb);
            jsonOb = new JSONObject();
        }
        return ResponseEntity.ok(jsonArray.toString());
    }

    @PostMapping(value = "sub05", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getSub05(WhereEntity whereEntity,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws NoSuchAlgorithmException, JsonProcessingException, IOException, JSONException, ParseException {
        // 매출대비입금조회 API

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Accept", "application/x-www-form-urlencoded");
        response.setHeader("Access-Control-Allow-Origin", "*");

        // 파라미터 설정
        String sappdd = whereEntity.getSappdd();
        String eappdd = whereEntity.getEappdd();
        String orgcd  = whereEntity.getOrgcd();
        String depcd  = whereEntity.getDepcd();


        String setWhere = "";


        // 날짜 조건검색
        setWhere += " AND APPDD IS NOT NULL AND APPDD BETWEEN '"+sappdd+"' AND '"+eappdd+"' ";

        // 사업부 조건 검색
        if(depcd==null||depcd.equals("")) {
            setWhere += " AND TID IS NOT NULL AND TID IN (SELECT TID FROM TB_BAS_TIDMAP  WHERE ORG_CD='"+orgcd+"') ";
        } else {
            setWhere += " AND TID IS NOT NULL AND TID IN (SELECT TID FROM TB_BAS_TIDMAP  WHERE DEP_CD='"+depcd+"' AND ORG_CD='"+orgcd+"') ";
        }


        ArrayList<Sub05Entity> sub05 = resultService.getSub05(orgcd, setWhere);

        // sub05에 저장된 데이터 뽑아서 json형식으로 변환
        JSONObject jsonOb = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Sub05Entity i : sub05) {


            // 날짜형식 변환
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyMMdd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yy.MM.dd");
            Date beforeSappdd = inputFormat.parse(i.getAppdd().substring(2,8));
            String AfterSappdd = outputFormat.format(beforeSappdd);



            jsonOb.put("appdd", AfterSappdd);
            jsonOb.put("dep", i.getDepnm());
            jsonOb.put("totsales", i.getTotsales());
            jsonOb.put("totfee", i.getTotfee());
            jsonOb.put("totsaleamt", i.getTotsaleamt());
            jsonOb.put("totreceivable", i.getTotreceivable());

            jsonArray.put(jsonOb);
            jsonOb = new JSONObject();
        }
        return ResponseEntity.ok(jsonArray.toString());
    }

    @PostMapping(value = "sub06", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getSub06(WhereEntity whereEntity,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws NoSuchAlgorithmException, JsonProcessingException, IOException, JSONException, ParseException {
        // 카드사별 조회 API

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Accept", "application/x-www-form-urlencoded");
        response.setHeader("Access-Control-Allow-Origin", "*");

        // 파라미터 설정
        String sexpdd = whereEntity.getSexpdd();
        String eexpdd = whereEntity.getEexpdd();
        String orgcd  = whereEntity.getOrgcd();
        String depcd  = whereEntity.getDepcd();
        String acqcd  = whereEntity.getAcqcd();




        String setWhere = "";
        String expddWhere = "";
        String acqWhere = "";

        // 날짜 조건검색

        // 사업부 조건 검색
        if(depcd==null||depcd.equals("")) {
            setWhere += "MID IN (SELECT MID FROM TB_BAS_MIDMAP  WHERE ORG_CD='"+orgcd+"') ";
        } else {
            setWhere += "MID IN (SELECT MID FROM TB_BAS_MIDMAP  WHERE DEP_CD='"+depcd+"' AND ORG_CD='"+orgcd+"') ";
        }
        expddWhere += "EXP_DD BETWEEN '"+sexpdd+"' AND '"+eexpdd+"'";


        // 카드사 조건 검색
        if(acqcd==null||acqcd.equals("")) {
            acqWhere += "";
        } else {
            acqWhere += " AND T7.PUR_KOCES IN('"+acqcd+"')";
        }

        ArrayList<Sub06Entity> sub06 = resultService.getSub06(orgcd, setWhere, expddWhere, acqWhere);

        // sub06에 저장된 데이터 뽑아서 json형식으로 변환
        JSONObject jsonOb = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Sub06Entity i : sub06) {


            // 날짜형식 변환
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyMMdd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yy.MM.dd");
            Date beforeSexpdd = inputFormat.parse(i.getExpdd().substring(2,8));
            String AfterSexpdd = outputFormat.format(beforeSexpdd);



            jsonOb.put("expdd", AfterSexpdd);
            jsonOb.put("dep", i.getDepnm());
            jsonOb.put("card", i.getAcqnm());
//            jsonOb.put("ACQCD", i.getAcqcd());
            jsonOb.put("amt", i.getTotamt());

            jsonArray.put(jsonOb);
            jsonOb = new JSONObject();
        }
        return ResponseEntity.ok(jsonArray.toString());
    }
}
