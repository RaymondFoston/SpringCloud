package com.practice.controller;

import com.practice.entities.PayDTD;
import com.practice.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class OrderCOntroller {
//    public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://cloud-payment-service";

    @Autowired
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

    @GetMapping(value = "/consumer/pay/get/info")
    private String getInfoByConsul(){
        return restTemplate.getForObject(PAYMENT_URL + "/pay/get/info", String.class);
    }

    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("/consumer/discovery")
    public String discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }


}

