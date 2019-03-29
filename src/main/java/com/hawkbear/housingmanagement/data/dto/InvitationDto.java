package com.hawkbear.housingmanagement.data.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author xch
 * @since 2019/3/29 16:28
 **/
@Data
public class InvitationDto {

    private Date invitationTime;        //预约时间

    private String nickName;    //看房人昵称

    private String phone;      //看房人电话

    private Integer status;    //当前邀请item 状态

    private Long id  ;     //邀请item  id

}
