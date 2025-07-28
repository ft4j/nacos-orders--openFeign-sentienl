package com.tuling.springcloud.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 接口统一返回实体类
 * @param <T> 数据类型
 */
@JsonInclude(JsonInclude.Include.NON_NULL) // 序列化时忽略null字段
@Data
public class ApiResponse<T> {
    // 状态码：200成功，其他为错误
    private int code;
    // 响应消息
    private String message;
    // 响应数据
    private T data;
    // 响应时间戳
    private String timestamp;

    // 构造方法私有化，通过静态方法创建实例
    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        // 格式化时间戳为yyyy-MM-dd HH:mm:ss
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // 成功响应（带数据、带成功的消息）
    public static <T> ApiResponse<T> success(String msg,T data) {
        return new ApiResponse<>(200, msg, data);
    }

    // 成功响应（带数据）
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "操作成功", data);
    }

    // 成功响应（无数据）
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(200, "操作成功", null);
    }

    // 错误响应（自定义状态码和消息）
    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    // 错误响应（默认500状态码）
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(500, message, null);
    }
}
