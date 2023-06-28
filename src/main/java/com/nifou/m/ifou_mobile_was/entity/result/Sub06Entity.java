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
public class Sub06Entity {
    @Id
    @Column
    private Long id;
    @Column
    @JsonProperty("expdd")
    private String expdd;
    @Column
    @JsonProperty("dep")
    private String depnm;
    @Column
    @JsonProperty("card")
    private String acqnm;
    @Column
    @JsonProperty("amt")
    private Long totamt;

    public Sub06Entity() {

    }
}
