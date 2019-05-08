package com.hawkbear.housingmanagement.service;

import com.hawkbear.housingmanagement.data.pojo.House;
import com.hawkbear.housingmanagement.data.pojo.Img;
import com.hawkbear.housingmanagement.data.vo.HouseVo;
import com.hawkbear.housingmanagement.holder.UserHolder;
import com.hawkbear.housingmanagement.mapper.HouseMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseService {

    @Resource
    private HouseMapper houseMapper;

    @Resource
    private ImgService imgService;

    @Resource
    private QiniuService qiniuService;

    @Resource
    private ClientService clientService;

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

    public HouseVo getHouseVo(Long houseId) {
        HouseVo houseVo = new HouseVo();
        House house = houseMapper.selectByPrimaryKey(houseId);
        houseVo.setHouse(house);
        Long sellerId = house.getSeller();
        houseVo.setUser(clientService.getUser(sellerId));
        Img profile = imgService.findImgByUserId(sellerId);
        if (null == profile) {
            houseVo.setProfile("http://cdn.stalary.com/2e8512a721.png");
        } else {
            houseVo.setProfile(profile.getImageUrl());
        }
        List<Img> imgList = imgService.findImgsByHouseId(houseId);
        List<String> imgUrlList = imgList.stream().map(Img::getImageUrl).collect(Collectors.toList());
        houseVo.setImgList(imgUrlList);
        return houseVo;
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

//    public PageInfo<House> findAllNormalHouseByPage(int page, int size) {
//        PageHelper.startPage(page,size);
//        Example example = new Example(House.class);
//        example.createCriteria().andEqualTo("status",Constants.HOUSE_NORMAL);
//        List<House> houseList = houseMapper.selectByExample(example);
//        PageInfo<House> pageInfo = new PageInfo<>(houseList);
//        return pageInfo;
//    }
}
