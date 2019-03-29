package com.hawkbear.housingmanagement.controller;

import com.github.pagehelper.PageInfo;
import com.hawkbear.housingmanagement.annotation.LoginRequired;
import com.hawkbear.housingmanagement.data.dto.SearchDto;
import com.hawkbear.housingmanagement.data.pojo.House;
import com.hawkbear.housingmanagement.data.vo.ResponseMessage;
import com.hawkbear.housingmanagement.data.vo.SearchVo;
import com.hawkbear.housingmanagement.service.HouseService;
import com.hawkbear.housingmanagement.service.ImgService;
import com.hawkbear.housingmanagement.utils.QiniuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private ImgService imgService;

    /**
     * 上传房屋照片
     * @param file
     * @return
     * @throws IOException
     */
    @LoginRequired
    public String uploadHouseImg(CommonsMultipartFile file) throws IOException {
        return QiniuUtil.upload(file.getInputStream());
    }


    /**
     * 添加房屋
     * @param address
     * @param area
     * @param type
     * @param imgUrl
     * @return
     */
    @LoginRequired
    @PostMapping("/addHouse")
    public ResponseMessage addHouse(String[] imgs,Long price,String desc,String address, Integer area, String type,String imgUrl){
        Long houseId = houseService.addHouse(price, desc, address, area, type, imgUrl);
        imgService.addImgs(imgs,houseId);
        return ResponseMessage.successMessage();
    }


    /**
     * 根据houseId 返回房屋细节信息
     * @param houseId
     * @return
     */
    @GetMapping("/toHouseDetail/{id}")
    public ResponseMessage findHouseById(@PathVariable("id") long houseId){
        return ResponseMessage.successMessage(houseService.findHouseDetail(houseId));
    }

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
