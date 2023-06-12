package com.nifou.m.ifou_mobile_was.entity.common;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class AcqEntity {
    @Column
    @JsonProperty("pur_nm")
    private String purnm;
    @Column
    @JsonProperty("pur_cd")
    @Id
    private String purcd;
    @Column
    @JsonProperty("pur_koces")
    private String purkoces;

    public AcqEntity() {

    }
}
