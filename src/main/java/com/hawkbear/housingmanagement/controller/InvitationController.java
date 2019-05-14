package com.hawkbear.housingmanagement.controller;

import com.hawkbear.housingmanagement.annotation.LoginRequired;
import com.hawkbear.housingmanagement.data.vo.ResponseMessage;
import com.hawkbear.housingmanagement.service.InvitationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/invitation")
public class InvitationController {

    @Resource
    private InvitationService invitationService;

    @PostMapping("/{houseId}")
    @LoginRequired
    public ResponseMessage addInvitation(@PathVariable("houseId") Long houseId) {
        boolean result = invitationService.addInvitation(houseId);
        if (result) {
            return ResponseMessage.successMessage("添加邀约成功");
        }
        return ResponseMessage.successMessage("该房屋已添加邀约");
    }

    @GetMapping("/buyer")
    @LoginRequired
    public ResponseMessage getBuyerInvitationList(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return ResponseMessage.successMessage(invitationService.getBuyerInvitationList(page, size));
    }

    @GetMapping("/seller")
    @LoginRequired
    public ResponseMessage getSellerInvitationList(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return ResponseMessage.successMessage(invitationService.getSellerInvitationList(page, size));
    }

    @PostMapping("/delete/{houseId}")
    @LoginRequired
    public ResponseMessage deleteInvitation(@PathVariable("houseId") Long houseId) {
        invitationService.deleteInvitation(houseId);
        return ResponseMessage.successMessage("删除邀约成功");
    }
}
