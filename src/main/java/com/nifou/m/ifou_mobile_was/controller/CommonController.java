package com.nifou.m.ifou_mobile_was.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nifou.m.ifou_mobile_was.entity.common.*;
import com.nifou.m.ifou_mobile_was.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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

@RestController
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private CommonService commonService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @PostMapping(value = "get_main_dashboard", produces = {MediaType.APPLICATION_JSON_VALUE})
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

    @PostMapping(value = "get_main_dashboard2", produces = {MediaType.APPLICATION_JSON_VALUE})
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

    @PostMapping(value = "get_menu.gaon", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList<MenuEntity>> getMenu(@RequestParam(value = "auth_seq", required=false) String authSeq,
                                                         @RequestParam(value = "orgcd", required=false) String orgCd,
                                                         HttpServletRequest request,
                                                         HttpServletResponse response) throws  IOException {
        // ORGCD, 권한에 맞는 메뉴정보 조회
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Accept", "application/x-www-form-urlencoded");
        response.setHeader("Access-Control-Allow-Origin", "*");

        ArrayList<MenuEntity> menu = commonService.getMenu(orgCd, authSeq);
        return new ResponseEntity<>(menu, HttpStatus.OK);

    }

    @PostMapping(value = "get_depcd", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList<DepEntity>> getDep( @RequestParam(value = "orgcd", required=false) String orgCd,
                                                         HttpServletRequest request,
                                                         HttpServletResponse response) throws  IOException {
        // ORGCD에 맞는 사업부코드 조회
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Accept", "application/x-www-form-urlencoded");
        response.setHeader("Access-Control-Allow-Origin", "*");

        ArrayList<DepEntity> dep = commonService.getDep(orgCd);
        return new ResponseEntity<>(dep, HttpStatus.OK);

    }

    @PostMapping(value = "get_acqcd", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList<AcqEntity>> getAcq(HttpServletRequest request,
                                                       HttpServletResponse response) throws IOException {
        // ORGCD에 맞는 카드사 조회
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Accept", "application/x-www-form-urlencoded");
        response.setHeader("Access-Control-Allow-Origin", "*");

        ArrayList<AcqEntity> acq = commonService.getAcq();
        return new ResponseEntity<>(acq, HttpStatus.OK);

    }

    @PostMapping(value = "get_tidcd", produces = {MediaType.APPLICATION_JSON_VALUE})
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


    @PostMapping(value = "columns.gaon.dhtml", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTbSysDomainDHTML( @RequestParam(value = "orgcd", required=false) String orgCd,
                                          @RequestParam(value = "pages", required=false) String pages,
                                                        HttpServletRequest request,
                                                        HttpServletResponse response) throws IOException, JSONException {
        // DHTML전용
        // ORGCD에 맞는 사업부코드 조회

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Accept", "application/x-www-form-urlencoded");
        response.setHeader("Access-Control-Allow-Origin", "*");

        ArrayList<ColumnsEntity> columns = commonService.getTbSysDomain(orgCd, pages);
        JSONObject jsonOb = new JSONObject();
        JSONArray jsonAllArray = new JSONArray();

        // ColumnsInfo에 저장된 데이터 뽑아서 json형식으로 변환
        for (ColumnsEntity i : columns) {
            jsonOb.put("width", i.getWidths());
            jsonOb.put("id", i.getPosfield());
            if (i.getColspan()==null || "".equals(i.getColspan()) ) {
                i.setColspan(0);
            }
            if (i.getRowspan()==null || "".equals(i.getRowspan())) {
                i.setRowspan(0);
            }
            // rowspan 컬럼이 0값이 아닐때
            if (i.getRowspan()!=0) {
                JSONObject jsonObHeader = new JSONObject();
                jsonObHeader.put("text", i.getFieldstxt());
                jsonObHeader.put("rowspan", i.getRowspan());
                jsonObHeader.put("align", i.getAligns());

                JSONArray jsonArray = new JSONArray();
                jsonArray.put(jsonObHeader);
                jsonOb.put("header", jsonArray);
            }

            // colspan 컬럼이 0값이 아닐때
            if (i.getColspan()!=0) {
                JSONObject jsonObColHeader = new JSONObject();
                JSONObject jsonObHeader = new JSONObject();
                jsonObHeader.put("text", i.getFieldstxt());
                jsonObHeader.put("colspan", i.getColspan());
                jsonObHeader.put("align", i.getAligns());

                jsonObColHeader.put("text2",i.getColtxt());

                JSONArray jsonArray = new JSONArray();
                jsonArray.put(jsonObHeader);
                jsonArray.put(jsonObColHeader);
                jsonOb.put("header", jsonArray);
            }


            if(i.getColspan()==0  && i.getRowspan()==0 && "Y".equals(i.getColchk())) {
                JSONObject jsonObHeader = new JSONObject();
                JSONObject jsonObColHeader = new JSONObject();
                jsonObHeader.put("text", i.getFieldstxt());
                jsonObHeader.put("align", i.getAligns());
                jsonObColHeader.put("text2", "");

                JSONArray jsonArray = new JSONArray();
                jsonArray.put(jsonObHeader);
                jsonArray.put(jsonObColHeader);
                jsonOb.put("header", jsonArray);

            }
            if(i.getColspan()==0  && i.getRowspan()==0 && !"Y".equals(i.getColchk())) {
                JSONObject jsonObHeader = new JSONObject();
                JSONObject jsonObColHeader = new JSONObject();
                jsonObHeader.put("text", i.getFieldstxt());
                jsonObHeader.put("align", i.getAligns());

                JSONArray jsonArray = new JSONArray();
                jsonArray.put(jsonObHeader);
                jsonOb.put("header", jsonArray);

            }
            if("int".equals(i.getSorts())) {
                jsonOb.put("format","#,###");
            }

            jsonAllArray.put(jsonOb);
            jsonOb = new JSONObject();
        }

        return ResponseEntity.ok(jsonAllArray.toString());
    }



    @PostMapping(value = "columns.gaon", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTbSysDomain( @RequestParam(value = "orgcd", required=false) String orgCd,
                                                  @RequestParam(value = "pages", required=false) String pages,
                                                  HttpServletRequest request,
                                                  HttpServletResponse response) throws IOException, JSONException {
        // 리액트전용 #ag-grid / react-data-grid
        // ORGCD에 맞는 사업부코드 조회

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Accept", "application/x-www-form-urlencoded");
        response.setHeader("Access-Control-Allow-Origin", "*");

        ArrayList<ColumnsEntity> columns = commonService.getTbSysDomain(orgCd, pages);
        JSONObject jsonOb = new JSONObject();
        JSONArray jsonAllArray = new JSONArray();

        // ColumnsInfo에 저장된 데이터 뽑아서 json형식으로 변환
        for (ColumnsEntity i : columns) {
            jsonOb.put("filed", i.getPosfield());
            jsonOb.put("headerName", i.getFieldstxt());
            if (i.getColspan()==null || "".equals(i.getColspan()) ) {
                i.setColspan(0);
            }
            if (i.getRowspan()==null || "".equals(i.getRowspan())) {
                i.setRowspan(0);
            }



            jsonAllArray.put(jsonOb);
            jsonOb = new JSONObject();
        }

        return ResponseEntity.ok(jsonAllArray.toString());
    }

}