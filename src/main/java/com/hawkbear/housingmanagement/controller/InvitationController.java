package com.hawkbear.housingmanagement.controller;

import com.hawkbear.housingmanagement.annotation.LoginRequired;
import com.hawkbear.housingmanagement.data.dto.User;
import com.hawkbear.housingmanagement.data.vo.InvitationSearchVo;
import com.hawkbear.housingmanagement.data.vo.ResponseMessage;
import com.hawkbear.housingmanagement.holder.UserHolder;
import com.hawkbear.housingmanagement.service.ClientService;
import com.hawkbear.housingmanagement.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;

@RestController
@RequestMapping("/invitation")
public class InvitationController {

    @Resource
    private ClientService clientService;

    @Autowired
    private InvitationService invitationService;


    /**
     * 根据房屋id获取邀请
     *
     * @param invitationSearchVo
     * @return
     */
    @LoginRequired
    @PostMapping("getInvitation")
    public ResponseMessage getInvitation(InvitationSearchVo invitationSearchVo) {
        try {
            return ResponseMessage.successMessage(invitationService.getInvitationBySearchVo(invitationSearchVo));
        } catch (ParseException e) {
            return ResponseMessage.failedMessage("传来日期格式有误");
        }
    }


    /**
     * 发送邀请 需登陆
     *
     * @param sellerId
     * @param houseId
     * @param time
     * @return
     */
//    @LoginRequired
//    @GetMapping("sendInvitation")
//    public ResponseMessage sendInvitation(Long sellerId, Long houseId, String time) {
//        User user = UserHolder.get();
//        if (invitationService.sendInvitation(user.getId(), sellerId, houseId, time) == 1)
//            return ResponseMessage.successMessage();
//        else
//            return ResponseMessage.failedMessage();
//    }


}
