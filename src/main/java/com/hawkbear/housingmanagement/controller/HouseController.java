package com.hawkbear.housingmanagement.controller;

import com.hawkbear.housingmanagement.annotation.LoginRequired;
import com.hawkbear.housingmanagement.data.dto.HouseSearchDto;
import com.hawkbear.housingmanagement.data.pojo.House;
import com.hawkbear.housingmanagement.data.vo.ResponseMessage;
import com.hawkbear.housingmanagement.service.HouseService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/house")
public class HouseController {

    @Resource
    private HouseService houseService;

    @PostMapping
    @LoginRequired
    public ResponseMessage addHouse(House house, String province, String city, String distribution, MultipartFile img, MultipartFile[] imgList) {
        houseService.addHouse(house, province, city, distribution, img, imgList);
        return ResponseMessage.successMessage("添加房屋成功");
    }

    @GetMapping
    @LoginRequired
    public ResponseMessage getHouseList(@RequestParam(required = false, defaultValue = "1") int page,
                                        @RequestParam(required = false, defaultValue = "10") int size) {
        return ResponseMessage.successMessage(houseService.getHouseList(page, size));
    }

    @GetMapping("/{houseId}")
    public ResponseMessage findHouseById(@PathVariable("houseId") Long houseId) {
        return ResponseMessage.successMessage(houseService.getHouseVo(houseId));
    }

    @PostMapping("/houseList")
    public ResponseMessage search(HouseSearchDto houseSearchDto,
                                  @RequestParam(required = false, defaultValue = "1") int page,
                                  @RequestParam(required = false, defaultValue = "10") int size) {
        return ResponseMessage.successMessage(houseService.search(houseSearchDto, page, size));
    }

    @PostMapping("/delete/{houseId}")
    public ResponseMessage deleteHouse(@PathVariable("houseId") Long houseId) {
        boolean result = houseService.deleteHouse(houseId);
        if (result) {
            return ResponseMessage.successMessage("删除房屋成功");
        }
        return ResponseMessage.failedMessage("存在与该房屋关联的邀约");
    }
}
