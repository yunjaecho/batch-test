package io.spring.xmlReader;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * Created by USER on 2017-11-28.
 */
public class JangbojaItem {
    private int ptnNo;

    private int pGdNo;

    private String pGdCd;

    private int cateNo;

    private String pCateNo;

    private String mngNm;

    private String dspNm;

    private String gdAttr;

    private String taxTypeCd;

    private String deelModelCd;

    private String gdDelv;

    private String gdDelvMo;

    private int saleStdPrc;

    private int salePrc;

    private int mallPrc;

    private int delvFee;

    private int delvPack;

    private int retDelvFee;

    private int excDelvFee;

    private String prodCompNm;

    private String origin;

    private String kword;

    private String std;

    private String delivery_type;

    private double gdFeeRate;

    private String staDt;

    private String endDt;

    private String gdNotiTypeCd;

    private String goodsLSavedPath;

    private String goodsLUrl;

    private String goodsLFileName;

    private String goodsMSavedPath;

    private String goodsMUrl;

    private String goodsMFileName;

    private String goodsSSavedPath;

    private String goodsSUrl;

    private String goodsSFileName;

    private String gdInfo;

    private int inQty;

    private String gdNoti01;

    private String gdNoti02;

    private String gdNoti03;

    private String gdNoti04;

    private String gdNoti05;

    private String gdNoti06;

    private String gdNoti07;

    private String gdNoti08;

    private String gdNoti09;

    private String gdNoti10;

    private String gdNoti11;

    private String isSales;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
