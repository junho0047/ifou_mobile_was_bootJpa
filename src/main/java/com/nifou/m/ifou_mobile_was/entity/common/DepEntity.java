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
public class DepEntity {
    @Column
    @JsonProperty("dep_nm")
    private String depnm;
    @Column
    @Id
    @JsonProperty("dep_cd")
    private String depcd;

    public DepEntity() {

    }
}
