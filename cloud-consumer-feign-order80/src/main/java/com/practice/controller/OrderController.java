package com.practice.controller;

import cn.hutool.core.date.DateUtil;
import com.practice.apis.PayFeignApi;
import com.practice.entities.PayDTD;
import com.practice.resp.ResultData;
import com.practice.resp.ReturnCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class OrderController {
//    public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://cloud-payment-service";
    @Autowired
    private PayFeignApi payFeignApi;


    @PostMapping("/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTD payDTD) {
        return payFeignApi.addOrder(payDTD);


    }
    @GetMapping("/feign/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id")Integer id) {
        return payFeignApi.getPayInfo(id);
    }
    @GetMapping("/feign/pay/get/test/{id}")
    public ResultData getPayInfoTest(@PathVariable("id")Integer id) {
        ResultData resultData = null;
         try{
             System.out.println("====调用开始====="+ DateUtil.now());
             resultData = payFeignApi.getPayInfoTest(id);
         }catch (Exception e){
             e.printStackTrace();
             System.out.println("====调用结束====="+ DateUtil.now());
             ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
         }
        return resultData;
    }


    /**
     * 测试负载均衡
     * @return
     */
    @GetMapping(value = "/feign/pay/get/info")
    private String getInfoByConsul(){
         return payFeignApi.getInfo();
    }




}

