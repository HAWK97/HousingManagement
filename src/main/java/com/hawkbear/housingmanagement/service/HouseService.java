package com.hawkbear.housingmanagement.service;

import com.github.pagehelper.PageHelper;
import com.hawkbear.housingmanagement.data.dto.HouseSearchDto;
import com.hawkbear.housingmanagement.data.pojo.House;
import com.hawkbear.housingmanagement.data.pojo.Img;
import com.hawkbear.housingmanagement.data.pojo.Invitation;
import com.hawkbear.housingmanagement.data.vo.HouseVo;
import com.hawkbear.housingmanagement.holder.UserHolder;
import com.hawkbear.housingmanagement.mapper.HouseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HouseService {

    @Resource
    private HouseMapper houseMapper;

    @Resource
    private ImgService imgService;

    @Resource
    private InvitationService invitationService;

    @Resource
    private QiniuService qiniuService;

    @Resource
    private ClientService clientService;

    public House findHouseById(Long houseId) {
        return houseMapper.selectByPrimaryKey(houseId);
    }

    public void addHouse(House house, String province, String city, String distribution, MultipartFile img, MultipartFile[] imgList) {
        house.setCreateTime(new Date());
        house.setUpdateTime(new Date());
        String oldAddress = house.getAddress();
        String newAddress = province + city + distribution + " " + oldAddress;
        house.setAddress(newAddress);
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
        String oldTypeImage = house.getTypeImage();
        String newTypeImage = oldTypeImage + "?imageView2/2/w/900/h/400";
        house.setTypeImage(newTypeImage);
        houseVo.setHouse(house);
        Long sellerId = house.getSeller();
        houseVo.setUser(clientService.getUser(sellerId));
        Img profile = imgService.findImgByUserId(sellerId);
        if (null == profile) {
            houseVo.setProfile("http://cdn.stalary.com/2e8512a721.png?imageView2/2/w/100/h/100");
        } else {
            houseVo.setProfile(profile.getImageUrl() + "?imageView2/2/w/100/h/100");
        }
        List<Img> imgList = imgService.findImgsByHouseId(houseId);
        List<String> imgUrlList = imgList.stream().map(Img::getImageUrl).map(imageUrl -> changeImageUrl(imageUrl)).collect(Collectors.toList());
        houseVo.setImgList(imgUrlList);
        return houseVo;
    }

    public Map<String, Object> getHouseList(int page, int size) {
        PageHelper.startPage(page, size);
        Example example = new Example(House.class);
        example.createCriteria().andEqualTo("seller", UserHolder.get().getId());
        List<House> houseList = houseMapper.selectByExample(example);
        //总条数
        int totalSize = houseMapper.selectCountByExample(example);
        log.info("卖家房屋总条数:{}", totalSize);
        //总页数
        int totalPage = totalSize % size == 0 ? totalSize / size : totalSize / size + 1;
        log.info("卖家房屋总页数:{}", totalPage);
        if (page <= 0) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        log.info("修正卖家房屋page参数:{}", page);
        Map<String, Object> res = new HashMap<>();
        res.put("houseList", houseList);
        res.put("currentPage", page);
        return res;
    }

    public boolean deleteHouse(Long houseId) {
        List<Invitation> invitationList = invitationService.findInvitationsByHouseId(houseId);
        if (invitationList.size() == 0) {
            houseMapper.deleteByPrimaryKey(houseId);
            return true;
        }
        return false;
    }

    public Map<String, Object> search(HouseSearchDto houseSearchDto, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = new Example(House.class);
        Example.Criteria criteria = example.createCriteria();
        if (!houseSearchDto.getAddress().equals("全部全部全部")) {
            criteria.andLike("address", houseSearchDto.getAddress() + "%");
        }
        if (!houseSearchDto.getLowPrice().equals(0L)) {
            criteria.andGreaterThan("price", houseSearchDto.getLowPrice());
        }
        if (!houseSearchDto.getHighPrice().equals(-1L)) {
            criteria.andLessThan("price", houseSearchDto.getHighPrice());
        }
        if (!houseSearchDto.getLowArea().equals(0L)) {
            criteria.andGreaterThan("area", houseSearchDto.getLowArea());
        }
        if (!houseSearchDto.getHighArea().equals(-1L)) {
            criteria.andLessThan("area", houseSearchDto.getHighArea());
        }
        if (!houseSearchDto.getType().equals("不限")) {
            criteria.andEqualTo("type", houseSearchDto.getType());
        }
        List<House> houseList = houseMapper.selectByExample(example);
        for (House house : houseList) {
            String oldTypeImage = house.getTypeImage();
            String newTypeImage = oldTypeImage + "?imageView2/2/w/400/h/200";
            house.setTypeImage(newTypeImage);
        }
        //总条数
        int totalSize = houseMapper.selectCountByExample(example);
        log.info("房屋总条数:{}", totalSize);
        //总页数
        int totalPage = totalSize % size == 0 ? totalSize / size : totalSize / size + 1;
        log.info("房屋总页数:{}", totalPage);
        if (page <= 0) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        log.info("修正房屋page参数:{}", page);
        Map<String, Object> res = new HashMap<>();
        res.put("houseList", houseList);
        res.put("currentPage", page);
        return res;
    }

    private String changeImageUrl(String imageUrl) {
        return imageUrl + "?imageView2/2/w/900/h/400";
    }
}
