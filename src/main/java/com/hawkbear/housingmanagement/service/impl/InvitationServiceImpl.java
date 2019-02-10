package com.hawkbear.housingmanagement.service.impl;

import com.hawkbear.housingmanagement.data.pojo.Invitation;
import com.hawkbear.housingmanagement.mapper.InvitationMapper;
import com.hawkbear.housingmanagement.service.InvitationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
        int num = invitationMapper.insert(invitation);
        return  num;
    }
}
