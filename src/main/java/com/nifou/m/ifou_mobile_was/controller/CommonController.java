package com.nifou.m.ifou_mobile_was.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nifou.m.ifou_mobile_was.entity.common.*;
import com.nifou.m.ifou_mobile_was.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private CommonService commonService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @PostMapping(value = "/get_main_dashboard", produces = {MediaType.APPLICATION_JSON_VALUE})
    public DashBoard1Entity get_main_dashboard(@RequestParam(value = "orgcd",required = false) String orgCd,
                                 @RequestParam(value = "appdd",required = false) String appDd,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws UnsupportedEncodingException {
        // 전일매출현황/월매출통계 조회

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Accept", "application/x-www-form-urlencoded");
        response.setHeader("Access-Control-Allow-Origin", "*");


        // 대시보드정보에 담을 객체 생성
        DashBoard1Entity dashBoard = new DashBoard1Entity();

        // 조회
        dashBoard = commonService.getDashBoard1(orgCd, appDd);

        return dashBoard;

    }

    @PostMapping(value = "/get_main_dashboard2", produces = {MediaType.APPLICATION_JSON_VALUE})
    public DashBoard2Entity get_main_dashboard2(@RequestParam(value = "orgcd",required = false) String orgCd,
                                                @RequestParam(value = "expdd",required = false) String expDd,
                                                HttpServletRequest request,
                                                HttpServletResponse response) throws UnsupportedEncodingException {
        // 당일입금현황 조회

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Accept", "application/x-www-form-urlencoded");
        response.setHeader("Access-Control-Allow-Origin", "*");


        // 대시보드정보에 담을 객체 생성
        DashBoard2Entity dashBoard = new DashBoard2Entity();

        // 조회
        dashBoard = commonService.getDashBoard2(orgCd, expDd);

        return dashBoard;

    }

    @PostMapping(value = "/get_menu.gaon", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList<MenuEntity>> getMenu(@RequestParam(value = "auth_seq", required=false) String authSeq,
                                                         @RequestParam(value = "orgcd", required=false) String orgCd,
                                                         HttpServletRequest request,
                                                         HttpServletResponse response) throws NoSuchAlgorithmException, JsonProcessingException, IOException {
        // ORGCD, 권한에 맞는 메뉴정보 조회
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Accept", "application/x-www-form-urlencoded");
        response.setHeader("Access-Control-Allow-Origin", "*");

        ArrayList<MenuEntity> menu = commonService.getMenu(orgCd, authSeq);
        return new ResponseEntity<>(menu, HttpStatus.OK);

    }

    @PostMapping(value = "/get_depcd", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList<DepEntity>> getDep( @RequestParam(value = "orgcd", required=false) String orgCd,
                                                         HttpServletRequest request,
                                                         HttpServletResponse response) throws NoSuchAlgorithmException, JsonProcessingException, IOException {
        // ORGCD에 맞는 사업부코드 조회
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Accept", "application/x-www-form-urlencoded");
        response.setHeader("Access-Control-Allow-Origin", "*");

        ArrayList<DepEntity> dep = commonService.getDep(orgCd);
        return new ResponseEntity<>(dep, HttpStatus.OK);

    }

    @PostMapping(value = "/get_acqcd", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList<AcqEntity>> getAcq(HttpServletRequest request,
                                                       HttpServletResponse response) throws NoSuchAlgorithmException, JsonProcessingException, IOException {
        // ORGCD에 맞는 카드사 조회
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Accept", "application/x-www-form-urlencoded");
        response.setHeader("Access-Control-Allow-Origin", "*");

        ArrayList<AcqEntity> acq = commonService.getAcq();
        return new ResponseEntity<>(acq, HttpStatus.OK);

    }

    @PostMapping(value = "/get_tidcd", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList<TidEntity>> getTid( @RequestParam(value = "orgcd", required=false) String orgCd,
                                                        HttpServletRequest request,
                                                        HttpServletResponse response) throws NoSuchAlgorithmException, JsonProcessingException, IOException {
        // ORGCD에 맞는 사업부코드 조회
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Accept", "application/x-www-form-urlencoded");
        response.setHeader("Access-Control-Allow-Origin", "*");

        ArrayList<TidEntity> tid = commonService.getTid(orgCd);
        return new ResponseEntity<>(tid, HttpStatus.OK);

    }

}
