package com.hawkbear.housingmanagement.data;

import lombok.Getter;

/**
 * 用于描述响应结果的枚举
 *
 * @author wangshuguang
 * @since 2019/01/28
 */
public enum ResultEnum {

    SYSTEM_ERROR(-999, "系统错误"),

    // 1开头为用户有关的错误
    NEED_LOGIN(1001, "未登陆");

    @Getter
    private Integer code;

    @Getter
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
