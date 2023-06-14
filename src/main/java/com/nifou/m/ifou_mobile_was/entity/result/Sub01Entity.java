package com.nifou.m.ifou_mobile_was.entity.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Sub01Entity {
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
    @JsonProperty("card")
    private String acqnm;
    @Column
    @JsonProperty("ACQCD")
    private String acqcd;
    @Column
    @JsonProperty("cnt")
    private String totcnt;
    @Column
    @JsonProperty("amt")
    private String totamt;


    public Sub01Entity() {

    }
}
