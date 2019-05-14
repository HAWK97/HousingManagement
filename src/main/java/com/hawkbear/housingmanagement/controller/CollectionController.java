package com.hawkbear.housingmanagement.controller;

import com.hawkbear.housingmanagement.annotation.LoginRequired;
import com.hawkbear.housingmanagement.data.vo.ResponseMessage;
import com.hawkbear.housingmanagement.service.CollectionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("collection")
public class CollectionController {

    @Resource
    private CollectionService collectionService;

    @PostMapping("/{houseId}")
    @LoginRequired
    public ResponseMessage addCollection(@PathVariable Long houseId) {
        boolean result = collectionService.addCollection(houseId);
        if (result) {
            return ResponseMessage.successMessage("添加收藏成功");
        }
        return ResponseMessage.successMessage("该房屋已收藏");
    }

    @GetMapping
    @LoginRequired
    public ResponseMessage getCollectionList(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return ResponseMessage.successMessage(collectionService.getCollectionList(page, size));
    }

    @PostMapping("/delete/{houseId}")
    @LoginRequired
    public ResponseMessage deleteCollection(@PathVariable Long houseId) {
        collectionService.deleteCollection(houseId);
        return ResponseMessage.successMessage("删除收藏成功");
    }
}
