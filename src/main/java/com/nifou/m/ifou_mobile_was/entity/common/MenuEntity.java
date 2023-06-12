package com.nifou.m.ifou_mobile_was.entity.common;

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
public class MenuEntity {

    @Id
    @Column
    @JsonProperty("PROGRAM_SEQ")
    private String programseq;
    @Column
    @JsonProperty("MENU_NAME")
    private String menuname;
    @Column
    @JsonProperty("MENU_DEPTH")
    private String menudepth;
    @Column
    @JsonProperty("PARENT_SEQ")
    private String parentseq;
    @Column
    @JsonProperty("AUTH_R")
    private String authr;
    @Column
    @JsonProperty("AUTH_C")
    private String authc;
    @Column
    @JsonProperty("AUTH_U")
    private String authu;
    @Column
    @JsonProperty("AUTH_D")
    private String authd;
    @Column
    @JsonProperty("MURL")
    private String murl;

    public MenuEntity() {

    }
}
