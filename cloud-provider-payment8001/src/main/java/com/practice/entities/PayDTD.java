package com.practice.entities;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class PayDTD implements Serializable {
    private Integer id;
    //支付流水号
    private String payNo;
    //订单流水号
    private String orderNo;
    //用户账号
    private Integer userId;
    //交易金额
    private BigDecimal amount;
}
