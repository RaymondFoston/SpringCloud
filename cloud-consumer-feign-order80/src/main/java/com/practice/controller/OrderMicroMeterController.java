package com.practice.controller;

import cn.hutool.core.util.IdUtil;
import com.practice.apis.PayFeignApi;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderMicroMeterController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/feign/pay/micrometer/{id}")
    public String Micrometer(@PathVariable("id") Integer id){
        return payFeignApi.Micrometer(id);
    }
}
