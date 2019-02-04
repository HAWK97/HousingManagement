package com.hawkbear.housingmanagement.controller;

import com.github.pagehelper.PageInfo;
import com.hawkbear.housingmanagement.data.vo.ResponseMessage;
import com.hawkbear.housingmanagement.data.vo.SearchVo;
import com.hawkbear.housingmanagement.pojo.House;
import com.hawkbear.housingmanagement.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @PostMapping("/findHouseBySearchVo")
    public ResponseMessage<PageInfo<House>> findHouseBySearchVo(int page, int size,SearchVo searchVo){
            PageInfo<House> pageInfo = houseService.findAllHouseByPage(page,size,searchVo);
            return ResponseMessage.successMessage(pageInfo);
    }
}
