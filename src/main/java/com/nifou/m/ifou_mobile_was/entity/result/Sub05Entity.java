package com.nifou.m.ifou_mobile_was.entity.result;

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
public class Sub05Entity {
    @Id
    @Column
    private Long id;
    @Column
    @JsonProperty("appdd")
    private String appdd;
    @Column
    @JsonProperty("dep")
    private String depnm;
    @Column
    @JsonProperty("cnt")
    private int totsales;
    @Column
    @JsonProperty("amt")
    private int totreceivable;

    public Sub05Entity() {

    }
}
