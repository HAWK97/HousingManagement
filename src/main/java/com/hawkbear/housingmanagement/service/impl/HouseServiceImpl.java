package com.hawkbear.housingmanagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hawkbear.housingmanagement.data.dto.HouseDto;
import com.hawkbear.housingmanagement.data.dto.SearchDto;
import com.hawkbear.housingmanagement.data.dto.User;
import com.hawkbear.housingmanagement.data.pojo.House;
import com.hawkbear.housingmanagement.data.pojo.Img;
import com.hawkbear.housingmanagement.mapper.HouseMapper;
import com.hawkbear.housingmanagement.mapper.ImgMapper;
import com.hawkbear.housingmanagement.service.ClientService;
import com.hawkbear.housingmanagement.service.HouseService;
import com.hawkbear.housingmanagement.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    @Resource
    private HouseMapper houseMapper;

    @Autowired
    private ClientService clientService;

    @Resource
    private ImgMapper imgMapper;


    @Override
    public PageInfo<House> findAllHouseByPage(int page, int size, SearchDto searchDto) {
        PageHelper.startPage(page,size);
        Example example = new Example(House.class);
        if (searchDto.getHighArea() != null){
            example.createCriteria().andLessThan("area",searchDto.getHighArea());
        }
        if (searchDto.getAddress() != null){
            example.createCriteria().andLike("address","%"+searchDto.getAddress()+"%");
        }
        if (searchDto.getHighPrice() != null){
            example.createCriteria().andLessThan("price",searchDto.getHighPrice());
        }
        if (searchDto.getLowArea() != null){
            example.createCriteria().andGreaterThan("area",searchDto.getLowArea());
        }
        if (searchDto.getLowPrice() != null){
            example.createCriteria().andGreaterThan("price",searchDto.getLowPrice());
        }
        if (searchDto.getType() != null){
            example.createCriteria().andEqualTo("type",searchDto.getType());
        }

        //房屋处于正常状态1 ，2为删除，3为出租
        example.createCriteria().andEqualTo("status",Constants.HOUSE_NORMAL);
        List<House> houseList = houseMapper.selectByExample(example);
        PageInfo<House> pageInfo = new PageInfo<>(houseList);
        return pageInfo;
    }

    @Override
    public HouseDto findHouseDetail(Long houseId) {
        HouseDto houseDto = new HouseDto();
        House house = houseMapper.selectByPrimaryKey(houseId);
        User user = clientService.getUser(house.getSeller());
        houseDto.setHouse(house);
        houseDto.setSellerPhoneNum(user.getPhone());
        houseDto.setTitleImg(house.getTypeImage());
        Example example = new Example(Img.class);
        example.createCriteria().andEqualTo("house_id",house.getId());
        List<Img> imgList = imgMapper.selectByExample(example);
        List<String> stringList = new ArrayList<>();
        for (Img img:imgList)
            stringList.add(img.getImageUrl());
        houseDto.setImgList(stringList);
        return houseDto;
    }

    @Override
    public PageInfo<House> findAllNormalHouseByPage(int page, int size) {
        PageHelper.startPage(page,size);
        Example example = new Example(House.class);
        example.createCriteria().andEqualTo("status",Constants.HOUSE_NORMAL);
        List<House> houseList = houseMapper.selectByExample(example);
        PageInfo<House> pageInfo = new PageInfo<>(houseList);
        return pageInfo;
    }
}
