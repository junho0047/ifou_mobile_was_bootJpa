package com.nifou.m.ifou_mobile_was.controller;

import com.nifou.m.ifou_mobile_was.common.SHA256Util;

import com.nifou.m.ifou_mobile_was.entity.user.UserEntity;
import com.nifou.m.ifou_mobile_was.entity.user.UserUauthEntity;
import com.nifou.m.ifou_mobile_was.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "login.gaon", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getUser(@RequestParam("userid") String userId,
                                          @RequestParam("userpw") String userPw,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws NoSuchAlgorithmException, JSONException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Accept", "application/x-www-form-urlencoded");
        response.setHeader("Access-Control-Allow-Origin", "*");


        // 비밀번호 암호화 작업
        SHA256Util sha256 = new SHA256Util();
        String encUserPw = sha256.encrypt(userPw);
        String rtnmsg = "9999";

        System.out.println(encUserPw);

        // 유저정보를 담을 객체 생성
        UserEntity uInfo = new UserEntity();
        UserUauthEntity uUauth = userService.getUserUauth(userId);

        // 유저정보 조회 후 저장 userId,userPw,depCd,orgCd,userLv,authSeq
        uInfo = userService.getUser(userId,encUserPw);

        System.out.println(uInfo);

        if(uInfo!=null) {
            if(uInfo.getUserPw().equals(encUserPw)){
                rtnmsg = "0000"; // 유저정보 일치

            } else {
                rtnmsg = "0001"; // 비밀번호 오류
            }
        } else {
            rtnmsg = "0002";    //아이디 오류
        }

        // 유저정보가 일치 할 경우 uauth 조회
        if(rtnmsg.equals("0000")) {
            uUauth = userService.getUserUauth(userId);

        }

        // userId|orgCd|depCd|orgNo|pTab|vTab|dTab|userLv|transNo|authSeq|userNm 형태로 저장 후 base64 인코딩
        String strUserUauth ="";
        String uauth_base64 ="";
        if(uUauth!=null) {
            strUserUauth = uUauth.toString();
            uauth_base64 =  Base64.getEncoder().encodeToString(strUserUauth.getBytes());
        } else {
            strUserUauth = "null";
            uauth_base64 = Base64.getEncoder().encodeToString(strUserUauth.getBytes());
        }

        // Json 파싱관련
        JSONObject jsonob = new JSONObject();
        jsonob.put("RTN_MSG",rtnmsg);
        jsonob.put("uauth", uauth_base64);

        return ResponseEntity.ok(jsonob.toString());
    }
}
