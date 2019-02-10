package com.hawkbear.housingmanagement.controller;

import com.hawkbear.housingmanagement.data.vo.ResponseMessage;
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

    /**
     * 添加收藏 需登陆
     * @param userId
     * @param houseId
     * @return
     */
    @GetMapping("addColletion")
    public ResponseMessage addColletion(Long userId,Long houseId){
        //TODO  userId 由后端服务器获取
        if (collectionService.addCollection(userId, houseId) == 1)
            return ResponseMessage.successMessage();
        else
            return ResponseMessage.failedMessage();
    }

}
