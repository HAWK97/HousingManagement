package com.hawkbear.housingmanagement.data.vo;

import com.hawkbear.housingmanagement.data.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应信息
 *
 * @author wangshuguang
 * @since 2019/01/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("响应信息")
public class ResponseMessage<T> {

    @ApiModelProperty("状态码")
    private int code;

    @ApiModelProperty("信息")
    private String msg;

    @ApiModelProperty("是否成功")
    private boolean success;

    @ApiModelProperty("数据")
    private T data;

    public ResponseMessage(int code, String msg, boolean success) {
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    public static ResponseMessage enumError(ResultEnum resultEnum) {
        return new ResponseMessage(resultEnum.getCode(), resultEnum.getMsg(), false);
    }

    public static ResponseMessage error(int code, String msg) {
        return new ResponseMessage<>(code, msg, false);
    }

    public static <T>ResponseMessage successMessage(T data) {
        return new ResponseMessage<>(0, "success", true, data);
    }

    public static ResponseMessage successMessage() {
        return new ResponseMessage<>(0, "success", true, null);
    }

    public static ResponseMessage failedMessage(String message) {
        return new ResponseMessage<>(1, message, false, null);
    }

    public static ResponseMessage failedMessage() {
        return new ResponseMessage<>(1, "failed", false, null);
    }
}
