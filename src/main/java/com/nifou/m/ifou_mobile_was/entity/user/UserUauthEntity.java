package com.nifou.m.ifou_mobile_was.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserUauthEntity {

        @Id
        @JsonProperty("userId")
        @Column
        private String userid;
        @JsonProperty("orgCd")
        @Column
        private String orgcd;
        @JsonProperty("depCd")
        @Column
        private String depcd;
        @JsonProperty("orgNo")
        @Column
        private String orgno;
        @JsonProperty("pTab")
        @Column
        private String ptab;
        @JsonProperty("vTab")
        @Column
        private String vtab;
        @JsonProperty("dTab")
        @Column
        private String dtab;
        @JsonProperty("userLv")
        @Column
        private String userlv;
        @JsonProperty("transNo")
        @Column
        private String transno;
        @JsonProperty("authSeq")
        @Column
        private String authseq;
        @JsonProperty("userNm")
        @Column
        private String usernm;

    public UserUauthEntity() {

    }

        @Override
        public String toString() {
                return userid + "|" +
                        orgcd + "|" +
                        depcd + "|" +
                        orgno + "|" +
                        ptab + "|" +
                        vtab + "|" +
                        dtab + "|" +
                        userlv + "|" +
                        transno + "|" +
                        authseq + "|" +
                        usernm + "|" ;
        }
}
