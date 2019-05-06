package com.hawkbear.housingmanagement.service;

import com.hawkbear.housingmanagement.data.pojo.House;
import com.hawkbear.housingmanagement.data.pojo.Img;
import com.hawkbear.housingmanagement.holder.UserHolder;
import com.hawkbear.housingmanagement.mapper.HouseMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class HouseService {

    @Resource
    private HouseMapper houseMapper;

    @Resource
    private ImgService imgService;

    @Resource
    private QiniuService qiniuService;

    public void addHouse(House house, MultipartFile img, MultipartFile[] imgList) {
        house.setCreateTime(new Date());
        house.setUpdateTime(new Date());
        String typeImage = qiniuService.upload(img);
        house.setTypeImage(typeImage);
        house.setSeller(UserHolder.get().getId());
        houseMapper.insert(house);
        Long houseId = house.getId();
        for (int i = 0; i < imgList.length; i++) {
            String imgUrl = qiniuService.upload(imgList[i]);
            Img image = new Img();
            image.setHouseId(houseId);
            image.setImageUrl(imgUrl);
            imgService.addImg(image);
        }
    }

//    public PageInfo<House> findAllHouseByPage(int page, int size, SearchDto searchDto) {
//        PageHelper.startPage(page,size);
//        Example example = new Example(House.class);
//        if (searchDto.getHighArea() != null){
//            example.createCriteria().andLessThan("area",searchDto.getHighArea());
//        }
//        if (searchDto.getAddress() != null){
//            example.createCriteria().andLike("address","%"+searchDto.getAddress()+"%");
//        }
//        if (searchDto.getHighPrice() != null){
//            example.createCriteria().andLessThan("price",searchDto.getHighPrice());
//        }
//        if (searchDto.getLowArea() != null){
//            example.createCriteria().andGreaterThan("area",searchDto.getLowArea());
//        }
//        if (searchDto.getLowPrice() != null){
//            example.createCriteria().andGreaterThan("price",searchDto.getLowPrice());
//        }
//        if (searchDto.getType() != null){
//            example.createCriteria().andEqualTo("type",searchDto.getType());
//        }
//
//        //房屋处于正常状态1 ，2为删除，3为出租
//        example.createCriteria().andEqualTo("status",Constants.HOUSE_NORMAL);
//        List<House> houseList = houseMapper.selectByExample(example);
//        PageInfo<House> pageInfo = new PageInfo<>(houseList);
//        return pageInfo;
//    }

    /**
     * 根据房子id获取房屋信息
     * @param houseId
     * @return
     */
//    public HouseDto findHouseDetail(Long houseId) {
//        HouseDto houseDto = new HouseDto();
//        House house = houseMapper.selectByPrimaryKey(houseId);
//        User user = clientService.getUser(house.getSeller());
//        houseDto.setHouse(house);
//        houseDto.setSellerPhoneNum(user.getPhone());
//        houseDto.setTitleImg(house.getTypeImage());
//        Example example = new Example(Img.class);
//        example.createCriteria().andEqualTo("id",house.getId());
//        List<Img> imgList = imgMapper.selectByExample(example);
//        List<String> stringList = new ArrayList<>();
//        for (Img img:imgList)
//            stringList.add(img.getImageUrl());
//        houseDto.setImgList(stringList);
//        houseDto.setSellerName(user.getNickname());
//        houseDto.setSellerId(user.getId());
//        //TODO   用户头像
//        houseDto.setUserProfile("../../img/test_house.jpg");
//        return houseDto;
//    }

//    public PageInfo<House> findAllNormalHouseByPage(int page, int size) {
//        PageHelper.startPage(page,size);
//        Example example = new Example(House.class);
//        example.createCriteria().andEqualTo("status",Constants.HOUSE_NORMAL);
//        List<House> houseList = houseMapper.selectByExample(example);
//        PageInfo<House> pageInfo = new PageInfo<>(houseList);
//        return pageInfo;
//    }
}
