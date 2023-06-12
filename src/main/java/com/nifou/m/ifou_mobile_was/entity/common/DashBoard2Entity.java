package com.nifou.m.ifou_mobile_was.entity.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="dashboard2")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class DashBoard2Entity {
    @Id
    @JsonProperty("SALE_AMT")
    @Column
    private String saleamt;
    @JsonProperty("FEE")
    @Column
    private String fee;
    @JsonProperty("SUM")
    @Column
    private String sum;
    public DashBoard2Entity() {

    }


}
