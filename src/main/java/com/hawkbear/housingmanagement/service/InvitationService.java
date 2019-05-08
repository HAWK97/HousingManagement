package com.hawkbear.housingmanagement.service;

import com.hawkbear.housingmanagement.data.dto.User;
import com.hawkbear.housingmanagement.data.pojo.Invitation;
import com.hawkbear.housingmanagement.mapper.InvitationMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvitationService {

    @Resource
    private InvitationMapper invitationMapper;

    @Autowired
    private ClientService clientService;

//    public int sendInvitation(Long userId, Long sellerId, Long houseId, String time) {
//        Invitation invitation = new Invitation();
//        invitation.setCreateTime(new Date());
//        invitation.setHouseId(houseId);
//        invitation.setInvitationTime(time);
//        invitation.setSellerId(sellerId);
//        invitation.setUpdateTime(new Date());
//        invitation.setUserId(userId);
//        invitation.setStatus(Constants.INVITATION_SEND);
//        int num = invitationMapper.insert(invitation);
//        return  num;
//    }

    /**
     * 多条件查询用户邀请函信息
     * @param searchVo
     * @return
     * @throws ParseException
     */
//    public List<InvitationDto> getInvitationBySearchVo(InvitationSearchVo searchVo) throws ParseException {
//        Example example = new Example(Invitation.class);
//        if (StringUtils.isNotBlank(searchVo.getHouseId())){
//            example.createCriteria().andEqualTo("house_id",Integer.valueOf(searchVo.getHouseId()));
//        }
//        if (StringUtils.isNotBlank(searchVo.getSellerId())){
//            example.createCriteria().andEqualTo("seller_id",Integer.valueOf(searchVo.getSellerId()));
//        }
//        if (StringUtils.isNotBlank(searchVo.getStatus())){
//            example.createCriteria().andEqualTo("status",Integer.valueOf(searchVo.getStatus()));
//        }
//        if (StringUtils.isNotBlank(searchVo.getCreateDateTime())){
//            example.createCriteria().andEqualTo("create_time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(searchVo.getCreateDateTime()));
//        }
//        List<Invitation> invitationList = invitationMapper.selectByExample(example);
//        List<InvitationDto> res = new ArrayList<>();
//        for (Invitation invitation : invitationList){
//            InvitationDto invitationDto = new InvitationDto();
//            invitationDto.setId(invitation.getId());
//            invitationDto.setInvitationTime(invitation.getCreateTime());
//            invitationDto.setStatus(invitation.getStatus());
//            User user = clientService.getUser(invitation.getUserId());
//            invitationDto.setNickName(user.getNickname());
//            invitationDto.setPhone(user.getPhone());
//            res.add(invitationDto);
//        }
//        return  res;
//    }

}
