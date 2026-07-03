package com.practice.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RateLimitController
{
    /**
     * 使用sentinel自带的限流
     */

    @GetMapping("/rateLimit/byUrl")
    public String byUrl()
    {
        return "按rest地址限流测试OK";
    }

    /**
     * 使用SentinelResource定制：blockHandler
     * @return
     */

    @GetMapping("/rateLimit/byResource")
    @SentinelResource(value = "BySentinelResource", blockHandler = "handleExceptionAction_1")
    public String byResource()
    {
        return " This is SentinelResource, Flow control test is successful.";
    }

    public  String handleExceptionAction_1(BlockException blockException)
    {
        return "This is flow control test. The service is unreachable";
    }

    /**
     * 使用SentinelResource定制：blockHandler + fallback
     * @param param
     * @return
     */

    @GetMapping("/rateLimit/byResourceWithParam")
    @SentinelResource(value = "BySentinelResourceWithParam",
            blockHandler = "handleExceptionAction_2",
            fallback = "fallbackAction")
    public String byResourceWithParam(@RequestParam(value = "param",required = false) Integer param)
    {
        if (param == null || param != -1){
            return " This is SentinelResource with param, Flow control test is successful.";
        }else {
            throw new RuntimeException("Because param is -1,you see the exception!");
        }
    }

    public  String handleExceptionAction_2(@PathVariable Integer param, BlockException blockException)
    {
        log.error("This is handleExceptionAction_2",blockException);
        return "This is flow control test with path param. This The service is unreachable";
    }
    public String fallbackAction(@PathVariable Integer param, Throwable throwable)
    {
        log.error("This is fallbackAction in console",throwable);
        return "This is fallback action.";
    }

    /**
     * 热点规则
     * @param p1
     * @param p2
     * @return
     */
    @GetMapping("/rateLimit/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "dealHandler_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2)
    {
        return "------testHotKey";
    }
    public String dealHandler_testHotKey(String p1,String p2,BlockException exception)
    {
        return "-----dealHandler_testHotKey";
    }

}
