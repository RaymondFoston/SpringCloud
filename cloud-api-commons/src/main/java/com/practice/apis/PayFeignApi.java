package com.practice.apis;

import com.practice.entities.Pay;
import com.practice.entities.PayDTD;
import com.practice.resp.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "cloud-gateway")
public interface PayFeignApi {

    @PostMapping("/pay/add")
    public ResultData addOrder(@RequestBody PayDTD payDTD);

    @GetMapping("/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id")Integer id);

    @GetMapping("/pay/get/test/{id}")
    public ResultData getPayInfoTest(@PathVariable("id")Integer id);

    /**
     * openfeign
     * @return
     */
    @GetMapping(value = "/pay/get/info")
    public String getInfo();

    /**
     * Resilience4j CircuitBreaker Example
     */
    @GetMapping(value = "/pay/circuit/{id}")
    public String circuit(@PathVariable("id") Integer id);

    /**
     * Resilience4j Bulkhead Example
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id);



    /**
     * Resilience4j Ratelimit Example
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/ratelimit/{id}")
    public String myRatelimit(@PathVariable("id") Integer id);

    /**
     * Example： Micrometer monitor the link
     * @param id
     * @return
     */
    @GetMapping("/pay/micrometer/{id}")
    public String Micrometer(@PathVariable("id") Integer id);

    /**
     * Gateway Test 1
     */
    @GetMapping(value = "/pay/gateway/get/{id}")
    public ResultData<Pay> getById(@PathVariable(value = "id") Integer id);
    /**
     * Gateway Test 2
     */
    @GetMapping(value = "/pay/gateway/get/info")
    public ResultData<String> getGateWayInfo();
}
