package com.practice.service;

import com.practice.entities.Pay;

import java.util.List;

public interface PayService {
    //添加支付交易信息
    public int add(Pay pay);
    //删除支付交易信息
    public int delete(Integer id);
    //修改支付交易信息
    public int update(Pay pay);
    //通过主键id查询支付交易信息
    public Pay getById(Integer id);
    //查询全部支付交易信息
    public List<Pay> getAll();
}
