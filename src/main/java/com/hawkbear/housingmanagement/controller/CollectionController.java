package com.hawkbear.housingmanagement.controller;

import com.hawkbear.housingmanagement.annotation.LoginRequired;
import com.hawkbear.housingmanagement.data.dto.User;
import com.hawkbear.housingmanagement.data.vo.ResponseMessage;
import com.hawkbear.housingmanagement.holder.UserHolder;
import com.hawkbear.housingmanagement.service.ClientService;
import com.hawkbear.housingmanagement.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private ClientService clientService;

    /**
     * 添加收藏 需登陆
     * @param houseId
     * @return
     */
    @GetMapping("addColletion")
    @LoginRequired
    public ResponseMessage addColletion(Long houseId){
        User user = UserHolder.get();
        if (collectionService.addCollection(user.getId(), houseId) == 1)
            return ResponseMessage.successMessage();
        else
            return ResponseMessage.failedMessage();
    }

}
