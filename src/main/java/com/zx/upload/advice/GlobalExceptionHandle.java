package com.zx.upload.advice;

import com.zx.upload.result.ResultWrapper;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhangxin
 * @date 2023-03-15 17:06
 */
@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(Exception.class)
    public ResultWrapper tokenException(Exception e) {
        return ResultWrapper.getFailBuilder().code(501).msg(e.getMessage()).build();

    }

}
