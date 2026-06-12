package com.practice.controller;

import cn.hutool.core.util.IdUtil;
import com.practice.apis.PayFeignApi;
import com.practice.entities.Pay;
import com.practice.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderGateWayController {

    @Resource
    private PayFeignApi payFeignApi;
    @GetMapping(value = "/feign/pay/gateway/get/{id}")
    public ResultData<Pay> getById(@PathVariable(value = "id") Integer id){
        return payFeignApi.getById(id);
    }

    @GetMapping(value = "/feign/pay/gateway/get/info")
    public ResultData<String> getGateWayInfo(){
        return payFeignApi.getGateWayInfo();
    }


}
