package com.hawkbear.housingmanagement.service.impl;

import com.hawkbear.housingmanagement.data.dto.InvitationDto;
import com.hawkbear.housingmanagement.data.pojo.Invitation;
import com.hawkbear.housingmanagement.data.vo.InvitationSearchVo;
import com.hawkbear.housingmanagement.mapper.InvitationMapper;
import com.hawkbear.housingmanagement.service.InvitationService;
import com.hawkbear.housingmanagement.utils.Constants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class InvitationServiceImpl implements InvitationService {

    @Resource
    private InvitationMapper invitationMapper;

    @Override
    public int sendInvitation(Long userId, Long sellerId, Long houseId, String time) {
        Invitation invitation = new Invitation();
        invitation.setCreateTime(new Date());
        invitation.setHouseId(houseId);
        invitation.setInvitationTime(time);
        invitation.setSellerId(sellerId);
        invitation.setUpdateTime(new Date());
        invitation.setUserId(userId);
        invitation.setStatus(Constants.INVITATION_SEND);
        int num = invitationMapper.insert(invitation);
        return  num;
    }

    //TODO   多条件查询用户邀请函信息
    @Override
    public List<InvitationDto> getInvitationBySearchVo(InvitationSearchVo searchVo) {
        return null;
    }
}
