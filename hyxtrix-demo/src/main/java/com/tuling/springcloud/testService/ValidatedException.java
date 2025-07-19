package com.tuling.springcloud.testService;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ValidatedException {
    @ExceptionHandler
    public String UnValidateException(MethodArgumentNotValidException e){

        System.out.println(e.getMessage());
        System.out.println(e.getBindingResult());
        System.out.println(e.getParameter());
        Map<String, Object> model = e.getBindingResult().getModel();
        return e.getMessage();
    }

}
