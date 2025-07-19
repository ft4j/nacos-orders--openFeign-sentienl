package com.tuling.springcloud.testService;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AaDto {
    @NotBlank(message = "名称不可以为空的")
    private String name;
    private Integer age;
    private String className;
}
