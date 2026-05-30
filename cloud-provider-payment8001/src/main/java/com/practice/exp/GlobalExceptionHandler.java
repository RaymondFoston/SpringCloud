package com.practice.exp;

import com.practice.resp.ResultData;
import com.practice.resp.ReturnCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 默认全局异常处理
     * @param e
     * @return
     */
    public ResultData<String> exception(Exception e){
        System.out.println("----come in GlobalExceptionHandler");
        log.error("全局异常信息exception:{}",e.getMessage(),e);
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(),e.getMessage());
    }
}
