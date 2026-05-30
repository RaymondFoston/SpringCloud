package com.practice.controller;

import com.practice.entities.PayDTD;
import com.practice.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderCOntroller {
    public static final String PAYMENT_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/consumer/pay/add")
    public ResultData addOrder(@RequestBody PayDTD payDTD) {
        return  restTemplate.postForObject(PAYMENT_URL + "/pay/add", payDTD, ResultData.class);
    }
    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id")Integer id) {
        return restTemplate.getForObject(PAYMENT_URL + "/pay/get/" + id, ResultData.class, id);
    }

    @PutMapping("/consumer/pay/update")
    public ResultData updatePayInfo(@RequestBody PayDTD payDTD) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PayDTD> entity = new HttpEntity<>(payDTD, headers);
        return restTemplate.exchange(PAYMENT_URL + "/pay/update", HttpMethod.PUT,entity, ResultData.class).getBody();
    }

    @DeleteMapping("/consumer/pay/delete/{id}")
    public ResultData deletePayInfo(@PathVariable("id")Integer id) {
        return restTemplate.exchange(PAYMENT_URL + "/pay/delete/" + id, HttpMethod.DELETE, null, ResultData.class).getBody();
    }




}

