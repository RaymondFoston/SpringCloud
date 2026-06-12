package com.practice.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.practice.entities.Pay;
import com.practice.resp.ResultData;
import com.practice.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class PayGateWayController {

    @Resource
    private PayService payService;


    @GetMapping(value = "/pay/gateway/get/{id}")
    public ResultData<Pay> getById(@PathVariable(value = "id") Integer id){
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @GetMapping(value = "/pay/gateway/get/info")
    public ResultData<String> getGateWayInfo(){
        return ResultData.success("Gate Way Info: " + IdUtil.simpleUUID());
    }

    @GetMapping(value="/pay/gateway/filter")
    @ResponseBody
    public Map<String,Object> getGateWayFilter(HttpServletRequest  request){
        Map<String, Object> result = new LinkedHashMap<>();
        Map<String, String> item1 = new TreeMap<>();
        Map<String, String> item2 = new HashMap<>();
        Map<String, Object> item3 = new HashMap<>();

        result.put("Date", DateUtil.now());
        result.put("Title","This is gateway filter test");
        Enumeration<String> headers = request.getHeaderNames();
        while(headers.hasMoreElements()) {
            String headName = headers.nextElement();
            String headValue = request.getHeader(headName);
            item1.put(headName,headValue);
            if(headName.equalsIgnoreCase("X-Request-Democracy") || headName.equalsIgnoreCase("X-Request-Freedom")){
                item2.put(headName,headValue);
            }
        }

        String customerId = request.getParameter("customerId");
        String customerName = request.getParameter("customerName");
        item3.put("CustomerId",customerId);
        item3.put("CustomerName",customerName);

        result.put("AllHeader",item1);
        result.put("SelectedHeader",item2);
        result.put("Parameter",item3);


        return result;
    }
}
