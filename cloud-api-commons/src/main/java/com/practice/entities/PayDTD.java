package com.practice.entities;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 一般而言，调用者不应该获悉服务提供者的entities资源并知道表结构关系，所以服务方给出的
 * 接口文档都应成为DTO
 */
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
