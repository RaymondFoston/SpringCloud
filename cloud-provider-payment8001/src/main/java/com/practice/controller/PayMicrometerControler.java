package com.practice.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayMicrometerControler {

    /**
     * Example： Micrometer monitor the link
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/micrometer/{id}")
    public String Micrometer(@PathVariable("id") Integer id){
        String text =  "Hello, Welcome Micrometer World!\nThe id is " + id +".\nAnd the service return " + IdUtil.simpleUUID() + ".";
        return text.replace("\n","<br>");
    }
}
