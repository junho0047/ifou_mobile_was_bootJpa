package com.nifou.m.ifou_mobile_was.entity.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="dashboard")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class DashBoard1Entity {
    @Id
    @JsonProperty("CC_AMT")
    @Column
    private String ccamt;
    @JsonProperty("CB_AMT")
    @Column
    private String cbamt;
    @JsonProperty("IC_AMT")
    @Column
    private String icamt;
    @JsonProperty("SUM_AMT")
    @Column
    private String sumamt;
    @JsonProperty("CC_CNT")
    @Column
    private String cccnt;
    @JsonProperty("CB_CNT")
    @Column
    private String cbcnt;
    @JsonProperty("IC_CNT")
    @Column
    private String iccnt;
    @JsonProperty("SUM_CNT")
    @Column
    private String sumcnt;

    public DashBoard1Entity() {

    }
}
