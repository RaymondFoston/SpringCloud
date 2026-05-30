package com.practice.controller;

import com.practice.entities.Pay;
import com.practice.entities.PayDTD;
import com.practice.resp.ResultData;
import com.practice.resp.ReturnCodeEnum;
import com.practice.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayControler {
    @Resource
    private PayService payService;

    @PostMapping(value = "/pay/add")
    @Operation(summary = "新增", description = "新增支付交易方法,json字符串作参数")
    public ResultData<String> addPay(@RequestBody Pay pay){
        System.out.println(pay);
        int i = payService.add(pay);
        return ResultData.success("成功插入" + i + "条记录");
    }

    @DeleteMapping(value = "/pay/delete/{id}")
    @Operation(summary = "删除", description = "删除支付交易方法")
    public ResultData<String> deletePay(@PathVariable("id") Integer id){
        int i = payService.delete(id);
        return ResultData.success("成功删除" + i + "条记录");
    }

    @PutMapping(value = "/pay/update")
    @Operation(summary = "修改", description = "修改支付交易方法")
    public ResultData<String> updatePay(@RequestBody PayDTD payDTD){
        Pay pay = payService.getById(payDTD.getId());
        BeanUtils.copyProperties(payDTD,pay);
        pay.setUpdateTime(new Date());
        int i = payService.update(pay);
        return ResultData.success("成功更新" + i + "条记录");
    }

    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "查询单个", description = "通过主键id查询支付交易信息")
    public ResultData<Pay> getById(@PathVariable(value = "id") Integer id){
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @GetMapping(value="/pay/getAll")
    @Operation(summary = "查询全部", description = "查询全部支付交易信息")
    public ResultData<List<Pay>> getAll(){
        if (true) throw new RuntimeException("Test  error：");
        List<Pay> payList = payService.getAll();
        return ResultData.success(payList);
    }

    @RequestMapping(value = "/pay/error", method = RequestMethod.GET)
    public ResultData<Integer> getPayError(){
        Integer i = Integer.valueOf(200);
        try{
            System.out.println("------------come here");
            int data = 10/0;
        }catch (Exception e){
            e.printStackTrace();
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(),e.getMessage());
        }
        return ResultData.success(i);
    }
}
