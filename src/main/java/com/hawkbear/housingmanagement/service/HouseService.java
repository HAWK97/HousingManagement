package com.hawkbear.housingmanagement.service;

import com.github.pagehelper.PageInfo;
import com.hawkbear.housingmanagement.data.dto.HouseDto;
import com.hawkbear.housingmanagement.data.vo.SearchVo;
import com.hawkbear.housingmanagement.data.pojo.House;

public interface HouseService {

    /**
     * 分页模糊搜索房屋列表
     * @param page  页面
     * @return
     */
    PageInfo<House> findAllHouseByPage(int page, int size, SearchVo searchVo);


    /**
     * 查看房屋详情
     * @param houseId
     * @return
     */
    HouseDto findHouseDetail(Long houseId);

}
