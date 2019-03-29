package com.hawkbear.housingmanagement.service;

import com.github.pagehelper.PageInfo;
import com.hawkbear.housingmanagement.data.dto.HouseDto;
import com.hawkbear.housingmanagement.data.dto.SearchDto;
import com.hawkbear.housingmanagement.data.pojo.House;

public interface HouseService {


    /**
     * 添加房子
     * @param price
     * @param desc
     * @param address
     * @param area
     * @param type
     * @param imgUrl
     */
    Long addHouse(Long price,String desc,String address, Integer area, String type, String imgUrl);


    /**
     * 分页模糊搜索房屋列表
     * @param page  页面
     * @return
     */
    PageInfo<House> findAllHouseByPage(int page, int size, SearchDto searchVo);


    /**
     * 查看房屋详情
     * @param houseId
     * @return
     */
    HouseDto findHouseDetail(Long houseId);

    /**
     * 分页查找所有 正常状态房屋
     * @param page
     * @param size
     * @return
     */
    PageInfo<House> findAllNormalHouseByPage(int page, int size);

}
