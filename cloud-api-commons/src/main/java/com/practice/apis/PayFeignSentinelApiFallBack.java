package com.practice.apis;

import com.practice.resp.ResultData;
import com.practice.resp.ReturnCodeEnum;
import org.springframework.stereotype.Component;

@Component
public class PayFeignSentinelApiFallBack implements PayFeignSentinelApi{
    @Override
    public ResultData getPayByOrderNo(String orderNo) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "Service downgrade is enabled due to service outages or unavailability.");
    }
}
