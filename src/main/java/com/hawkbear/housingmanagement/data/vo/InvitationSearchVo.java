package com.hawkbear.housingmanagement.data.vo;

import lombok.Data;

/**
 * invitation 多条件查询
 * @author xch
 * @since 2019/3/29 16:49
 **/
@Data
public class InvitationSearchVo {

    private String status;      //邀请item 的状态

    private String houseId;    //房屋id

    private String  createDateTime;       //创建时间

    private String sellerId;       //卖房人id


}
