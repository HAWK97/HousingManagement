package com.hawkbear.housingmanagement.controller;

import com.github.pagehelper.PageInfo;
import com.hawkbear.housingmanagement.data.pojo.House;
import com.hawkbear.housingmanagement.data.vo.ResponseMessage;
import com.hawkbear.housingmanagement.data.dto.SearchDto;
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
    public ResponseMessage<PageInfo<House>> findHouseBySearchVo(@RequestParam(required = false,defaultValue = "1") int page,
                                                                @RequestParam(required = false,defaultValue = "10") int size,
                                                                SearchVo searchVo) {
        SearchDto searchDto = getStandardSearchVo(searchVo);
        PageInfo<House> pageInfo = houseService.findAllHouseByPage(page, size, searchDto);
        return ResponseMessage.successMessage(pageInfo);
    }

    @GetMapping("findAllNormalHouse")
    public ResponseMessage<PageInfo<House>> findAllNormalHouse(@RequestParam(required = false, defaultValue = "1") int page,
                                                               @RequestParam(required = false, defaultValue = "10") int size) {
        PageInfo<House> pageInfo = houseService.findAllNormalHouseByPage(page, size);
        return ResponseMessage.successMessage(pageInfo);
    }


    /**
     * 根据前端传来的搜索信息构建出一个符合搜索标准的SearchVo
     * @param searchVo
     * @return
     */
    private SearchDto getStandardSearchVo(SearchVo searchVo){
            SearchDto searchDto = new SearchDto();
            searchDto.setAddress(searchVo.getAddress());
            searchDto.setHighArea(searchVo.getHighArea());
            searchDto.setHighPrice(searchVo.getHighPrice());
            searchDto.setLowArea(searchVo.getLowArea());
            searchDto.setType(searchVo.getType());
            searchDto.setLowPrice(searchVo.getLowPrice());
            return searchDto;
    }
}
