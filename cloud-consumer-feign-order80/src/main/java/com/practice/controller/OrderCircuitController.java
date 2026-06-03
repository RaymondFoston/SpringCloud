package com.practice.controller;

import com.practice.apis.PayFeignApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderCircuitController {
    @Resource
    private PayFeignApi payFeignApi;

    //circuitbreaker
    @GetMapping(value = "/feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "")
    public String circuitBreaker(@PathVariable(value = "id") Integer id){
        return payFeignApi.circuit(id);
    }
    //服务降级后的处理方法
    public String circuitFallback(Integer id, Throwable t){
        //容错处理逻辑
        return "myCircuitFallback！The system is busy, please try again later.";
    }
}
