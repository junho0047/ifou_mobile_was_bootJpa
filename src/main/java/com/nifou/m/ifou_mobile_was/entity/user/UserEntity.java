package com.nifou.m.ifou_mobile_was.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="user")
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table(name="TB_BAS_USER")
public class UserEntity {
    @Id
    @JsonProperty("userId")
    @Column
    private String userId;
    @JsonProperty("userPw")
    @Column
    private String userPw;
    @JsonProperty("depCd")
    @Column
    private String depCd;
    @JsonProperty("orgCd")
    @Column
    private String orgCd;
    @JsonProperty("userLv")
    @Column
    private String userLv;
    @JsonProperty("authSeq")
    @Column
    private String authSeq;

    public UserEntity() {

    }
}
