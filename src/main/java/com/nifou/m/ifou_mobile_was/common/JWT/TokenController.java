package com.nifou.m.ifou_mobile_was.common.JWT;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TokenController {
    @Autowired
    private final JwtProvider jwtProvider;

    //==토큰 생성 컨트롤러==//
    @PostMapping(value = "tokenCreate", produces = {MediaType.APPLICATION_JSON_VALUE})
    public TokenResponse createToken(String rtnmsg, String uUauth) throws Exception {
        String token = jwtProvider.createToken(uUauth); // 토큰 생성
        Claims claims = jwtProvider.parseJwtToken("Bearer " + token); // 토큰 검증
        String errorMsg = "error";
        if(rtnmsg=="0000") {
           errorMsg = "OK";
        }
        TokenDataResponse tokenDataResponse = new TokenDataResponse(token, claims.getSubject(), claims.getIssuedAt().toString(), claims.getExpiration().toString());
//        TokenDataResponse tokenDataResponse = new TokenDataResponse(token, claims.getSubject(), claims.getIssuedAt().toString(), claims.getExpiration().toString());
        TokenResponse tokenResponse = new TokenResponse(rtnmsg, errorMsg, tokenDataResponse.token);

        return tokenResponse;
    }

    //==토큰 인증 컨트롤러==//
    @PostMapping(value = "checkToken")
    public TokenResponseNoData checkToken(@RequestHeader(value = "Authorization") String token) throws Exception {
        Claims claims = jwtProvider.parseJwtToken(token);

        TokenResponseNoData tokenResponseNoData = new TokenResponseNoData("200","success");
        return tokenResponseNoData;
    }

    //==Response DTO==//
    @Data
    @AllArgsConstructor
    public class TokenResponse<T> {

        @JsonProperty("RTN_MSG")
        private String RTN_MSG;
        @JsonProperty("MSG")
        private String msg;
        @JsonProperty("TOKEN")
        private T Token;
    }

    //==Response DTO==//
    @Data
    @AllArgsConstructor
    static class TokenResponseNoData<T> {

        private String code;
        private String msg;
    }

    //==Response DTO==//
    @Data
    @AllArgsConstructor
    static class TokenDataResponse {
        private String token;
        private String subject;
        private String issued_time;
        private String expired_time;
    }
}