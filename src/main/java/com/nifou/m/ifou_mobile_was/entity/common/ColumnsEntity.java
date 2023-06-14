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
public class ColumnsEntity {
    @JsonProperty("FIELDS_TXT")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column
    private String fieldstxt;

    @JsonProperty("ALIGNS")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column
    private String aligns;
    @JsonProperty("COL_TYPE")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column
    private String coltype;

    @JsonProperty("SORTS")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column
    private String sorts;

    @JsonProperty("WIDTHS")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column
    private String widths;
    @Id
    @JsonProperty("POS_FIELD")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column
    private String posfield;

    @JsonProperty("VAN_FIELD")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column
    private String vanfield;

    @JsonProperty("CASH_FIELD")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column
    private String cashfield;

    @JsonProperty("PAGES")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column
    private String pages;

    @JsonProperty("ORN")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column
    private String orn;

    @JsonProperty("ROWSPAN")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column
    private Integer rowspan;

    @JsonProperty("COLSPAN")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column
    private Integer colspan;
    @JsonProperty("COL_CHK")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column
    private String colchk;

    @JsonProperty("COL_TXT")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column
    private String coltxt;

    public ColumnsEntity() {

    }
}
