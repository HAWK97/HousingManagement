package com.hawkbear.housingmanagement.controller;

import com.github.pagehelper.PageInfo;
import com.hawkbear.housingmanagement.data.pojo.House;
import com.hawkbear.housingmanagement.data.vo.ResponseMessage;
import com.hawkbear.housingmanagement.data.vo.SearchVo;
import com.hawkbear.housingmanagement.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @PostMapping("/findHouseBySearchVo")
    public ResponseMessage<PageInfo<House>> findHouseBySearchVo(int page, int size, SearchVo searchVo) {
        PageInfo<House> pageInfo = houseService.findAllHouseByPage(page, size, searchVo);
        return ResponseMessage.successMessage(pageInfo);
    }

    @GetMapping("findAllNormalHouse")
    public ResponseMessage<PageInfo<House>> findAllNormalHouse(@RequestParam(required = false, defaultValue = "1") int page,
                                                               @RequestParam(required = false, defaultValue = "10") int size) {
        PageInfo<House> pageInfo = houseService.findAllNormalHouseByPage(page, size);
        return ResponseMessage.successMessage(pageInfo);
    }
}
