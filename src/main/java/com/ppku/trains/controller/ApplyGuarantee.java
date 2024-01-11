package com.ppku.trains.controller;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;


@Data
@Accessors(chain = true)
public class ApplyGuarantee{

    private String applyNo; // 申请编号
    private String assured; //被保险人
    private String drivingLicenseOwner; // 行驶证车主
    private String applicant; //投保人
    private String  vin;  //车架号
    private String engineNo; //发动机号
    private String useCharacter; //使用性质
    private BigDecimal  damageCoverage; //车损保险额
    private BigDecimal threeLiabilityInsurance;  // 三责险
    private String policyDate;  //保单生效日期
    private String submitDate; // 提交日期

    private String jsonData;


    private String title;


    private String userRealName;


    private Date riskPassDate;
}
