package com.hawkbear.housingmanagement.controller;

import com.hawkbear.housingmanagement.data.vo.ResponseMessage;
import com.hawkbear.housingmanagement.service.ClientService;
import com.hawkbear.housingmanagement.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/invitation")
public class InvitationController {

    @Resource
    private ClientService clientService;

    @Autowired
    private InvitationService invitationService;

    /**
     * 发送邀请 需登陆
     * @param userId
     * @param sellerId
     * @param houseId
     * @param time
     * @return
     */
    @GetMapping("sendInvitation")
    public ResponseMessage sendInvitation(Long userId, Long sellerId, Long houseId, String time){
        //TODO  userId 由后端服务器获取
        if (invitationService.sendInvitation(userId, sellerId, houseId, time) == 1)
            return ResponseMessage.successMessage();
        else
            return ResponseMessage.failedMessage();
    }
}
