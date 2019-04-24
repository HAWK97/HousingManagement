package com.hawkbear.housingmanagement.data.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户类
 *
 * @author wangshuguang
 * @since 2019/01/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户对象")
public class User {

    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty("用户名，要求唯一")
    private String username;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("头像url")
    private String avatarUrl;

    @ApiModelProperty(hidden = true)
    private Long projectId;

    @ApiModelProperty("角色，1 为买家，2 为卖家")
    private Integer role;

    @ApiModelProperty("短信验证码")
    private String code;

    @ApiModelProperty("是否保持登录状态，默认为 false")
    private Boolean remember = false;
}
