package com.hawkbear.housingmanagement.controller;

import com.hawkbear.housingmanagement.annotation.LoginRequired;
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

    @LoginRequired
    @PostMapping
    public ResponseMessage addHouse(House house, MultipartFile img, MultipartFile[] imgList) {
        houseService.addHouse(house, img, imgList);
        return ResponseMessage.successMessage();
    }


//    @GetMapping("/toHouseDetail/{id}")
//    public ResponseMessage findHouseById(@PathVariable("id") long houseId) {
//        return ResponseMessage.successMessage(houseService.findHouseDetail(houseId));
//    }
//    @PostMapping("/findHouseBySearchVo")
//    public ResponseMessage<PageInfo<House>> findHouseBySearchVo(@RequestParam(required = false, defaultValue = "1") int page,
//                                                                @RequestParam(required = false, defaultValue = "10") int size,
//                                                                SearchVo searchVo) {
//        SearchDto searchDto = getStandardSearchVo(searchVo);
//        PageInfo<House> pageInfo = houseService.findAllHouseByPage(page, size, searchDto);
//        return ResponseMessage.successMessage(pageInfo);
//    }

//    @GetMapping("findAllNormalHouse")
//    public ResponseMessage<PageInfo<House>> findAllNormalHouse(@RequestParam(required = false, defaultValue = "1") int page,
//                                                               @RequestParam(required = false, defaultValue = "10") int size) {
//        PageInfo<House> pageInfo = houseService.findAllNormalHouseByPage(page, size);
//        return ResponseMessage.successMessage(pageInfo);
//    }
}
