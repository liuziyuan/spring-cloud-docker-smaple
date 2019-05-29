package com.floe.shirosecurity.exception;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.floe.shirosecurity.common.ResponseResult;
import com.floe.shirosecurity.common.ResponseResultUtil;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler({AuthorizationException.class})    //申明捕获那个异常类
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
    public ResponseResult globalAuthorizationExceptionHandler(AuthorizationException e) {
		Map<String, Object> map = new HashMap<>();
        this.logger.error(e.getMessage(), e);
        map.put("error", e.getMessage());
        return ResponseResultUtil.buildError("x0000", e.getMessage());
    }
}
