package com.urfread.breaknews.core.common.enums;

import java.util.Arrays;

/**
 * Class Description:
 * This enum class is used to represent standardized result codes and messages for various responses in the application.
 * It provides a mapping between an HTTP-like status code and a corresponding message in both English and Chinese.
 * Additional methods are provided to fetch the enum instance by code in a fluent manner.
 *
 * @author urfread
 * @date 2024-08-24 08:05
 */
public enum ResultCodeEnum {
    SUCCESS("200", "成功"),
    FAIL("400", "失败"),
    UNAUTHORIZED("401", "未认证"),
    FORBIDDEN("403", "禁止访问"),
    NOT_FOUND("404", "接口不存在"),
    METHOD_NOT_ALLOWED("405", "请求方法不允许"),
    REQUEST_TIMEOUT("408", "请求超时"),
    CONFLICT("409", "冲突"),
    UNSUPPORTED_MEDIA_TYPE("415", "不支持的媒体类型"),
    TOO_MANY_REQUESTS("429", "请求过多"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误"),
    SERVICE_UNAVAILABLE("503", "服务不可用"),
    UNFINISHED("607","后端代码还没写好"),
    VALIDATION_FAILED("608", "校验不通过"),
    UNKNOWN("999", "未知错误");


    private final String code;
    private final String message;

    ResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Retrieves the corresponding ResultCodeEnum instance by the given code.
     *
     * @param code the code to search for
     * @return the corresponding ResultCodeEnum, or null if not found
     */
    public static ResultCodeEnum getResultCode(String code) {
        return Arrays.stream(ResultCodeEnum.values())
                .filter(x -> x.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static void main(String[] args) {
        System.out.println(ResultCodeEnum.getResultCode("200").getMessage());
        System.out.println(ResultCodeEnum.getResultCode("404").getMessage());
        System.out.println(ResultCodeEnum.getResultCode("500").getMessage());
    }
}