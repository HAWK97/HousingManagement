package com.hawkbear.housingmanagement.exception;

import com.hawkbear.housingmanagement.data.ResultEnum;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义异常类
 *
 * @author wangshuguang
 * @since 2018/01/29
 */
@Slf4j
public class MyException extends RuntimeException {

    @Getter
    private Integer code;

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
        log.warn("exception!", resultEnum.getMsg());
    }

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
        log.warn("exception!", message);
    }
}