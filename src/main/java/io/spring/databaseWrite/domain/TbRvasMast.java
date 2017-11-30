package io.spring.databaseWrite.domain;

import lombok.Data;

/**
 * Created by USER on 2017-11-30.
 */
@Data
public class TbRvasMast {
    private String orgBank;

    private String orgCd;

    private String vacctNo;

    private String statCd;

    private String inGb;

    private double  payAmt;

    private String payFromDate;

    private String payToDate;

    private String payToTime;

    private String custCd;

    private String custNm;

    private String entryDate;

    private String entryIdno;
}
