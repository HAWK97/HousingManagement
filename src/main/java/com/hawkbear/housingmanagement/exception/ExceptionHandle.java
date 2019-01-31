package com.hawkbear.housingmanagement.exception;

import com.hawkbear.housingmanagement.data.ResultEnum;
import com.hawkbear.housingmanagement.data.vo.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理类
 *
 * @author wangshuguang
 * @since 2019/01/29
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseMessage handle(Exception e) {
        if (e instanceof MyException) {
            MyException myException = (MyException) e;
            return ResponseMessage.error(myException.getCode(), myException.getMessage());
        } else {
            log.error("[系统异常] {}", e);
            return ResponseMessage.enumError(ResultEnum.SYSTEM_ERROR);
        }
    }
}
