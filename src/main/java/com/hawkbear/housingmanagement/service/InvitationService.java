package com.hawkbear.housingmanagement.service;

import com.hawkbear.housingmanagement.data.dto.InvitationDto;
import com.hawkbear.housingmanagement.data.vo.InvitationSearchVo;

import java.text.ParseException;
import java.util.List;

public interface InvitationService {



    /**
     *
     * @param userId 用户id
     * @param sellerId 出售房屋人id
     * @param houseId 房屋id
     * @param time 邀请时间
     */
    int  sendInvitation(Long userId, Long sellerId, Long houseId, String time);


    /**
     * 多条件查询invitation
     * @param searchVo
     * @return
     */
    List<InvitationDto> getInvitationBySearchVo(InvitationSearchVo searchVo) throws ParseException;
}
