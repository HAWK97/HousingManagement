package com.hawkbear.housingmanagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hawkbear.housingmanagement.data.vo.SearchVo;
import com.hawkbear.housingmanagement.mapper.HouseMapper;
import com.hawkbear.housingmanagement.pojo.House;
import com.hawkbear.housingmanagement.service.HouseService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    @Resource
    private HouseMapper houseMapper;

    @Override
    public PageInfo<House> findAllHouseByPage(int page, int size, SearchVo searchVo) {
        PageHelper.startPage(page,size);
        Example example = new Example(House.class);
        if (searchVo.getHighArea() != null){
            example.createCriteria().andLessThan("area",searchVo.getHighArea());
        }
        if (searchVo.getHighPrice() != null){
            example.createCriteria().andLessThan("price",searchVo.getHighPrice());
        }
        if (searchVo.getLowArea() != null){
            example.createCriteria().andGreaterThan("area",searchVo.getLowArea());
        }
        if (searchVo.getLowPrice() != null){
            example.createCriteria().andGreaterThan("price",searchVo.getLowPrice());
        }
        if (searchVo.getType() != null){
            example.createCriteria().andEqualTo("type",searchVo.getType());
        }
        if (searchVo.isOrderByArea() == false){
            example.setOrderByClause("area asc");
        }else {
            example.setOrderByClause("area desc");
        }
        if (searchVo.isOrderByPrice() == false){
            example.setOrderByClause("price asc");
        }else {
            example.setOrderByClause("price desc");
        }
        //房屋处于正常状态1 ，2为删除，3为出租
        example.createCriteria().andNotEqualTo("status",1);
        List<House> houseList = houseMapper.selectByExample(example);
        PageInfo<House> pageInfo = new PageInfo<>(houseList);
        return pageInfo;
    }
}
