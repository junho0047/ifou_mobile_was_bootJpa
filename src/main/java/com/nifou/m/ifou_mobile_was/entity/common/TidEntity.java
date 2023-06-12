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
public class TidEntity {
    @Column
    @JsonProperty("tid_nm")
    private String tidnm;

    @Id
    @Column
    @JsonProperty("tid_cd")
    private String tidcd;

    public TidEntity() {

    }
}
