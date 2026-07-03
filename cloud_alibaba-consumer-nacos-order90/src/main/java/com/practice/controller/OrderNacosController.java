package com.practice.controller;

import com.practice.apis.PayFeignSentinelApi;
import com.practice.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderNacosController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PayFeignSentinelApi payFeignSentinelApi;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping("/consumer/pay/nacos/{id}")
    public String paymentInfo(@PathVariable(value = "id") Integer id){
        String result = "This is consumer OrderNacosController ,the port is 90.\n";
        result += "Here are the results of the call from the provider: ";
        result +=  restTemplate.getForObject(serverURL+"/pay/nacos/"+id,String.class);
        return result.replace("\n","<br>");
    }

    @GetMapping("/consumer/payFeign/nacos/get/{orderNo}")
    public ResultData getPayByOrderNo(@PathVariable("orderNo") String orderNo){
        return payFeignSentinelApi.getPayByOrderNo(orderNo);
    };


}
