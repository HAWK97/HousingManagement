package com.hawkbear.housingmanagement.service;

import com.github.pagehelper.PageHelper;
import com.hawkbear.housingmanagement.data.pojo.Collection;
import com.hawkbear.housingmanagement.data.pojo.House;
import com.hawkbear.housingmanagement.holder.UserHolder;
import com.hawkbear.housingmanagement.mapper.CollectionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;

@Service
@Slf4j
public class CollectionService {

    @Resource
    private CollectionMapper collectionMapper;

    @Resource
    private HouseService houseService;

    public boolean addCollection(Long houseId) {
        Example example = new Example(Collection.class);
        example.createCriteria().andEqualTo("houseId", houseId);
        Collection collection = collectionMapper.selectOneByExample(example);
        if (collection == null) {
            collection = new Collection();
            collection.setHouseId(houseId);
            collection.setCreateTime(new Date());
            collection.setUpdateTime(new Date());
            collection.setUserId(UserHolder.get().getId());
            collectionMapper.insert(collection);
            return true;
        }
        return false;
    }

    public Map<String, Object> getCollectionList(int page, int size) {
        PageHelper.startPage(page, size);
        Example example = new Example(Collection.class);
        example.createCriteria().andEqualTo("userId", UserHolder.get().getId());
        List<Collection> collectionList = collectionMapper.selectByExample(example);
        //总条数
        int totalSize = collectionMapper.selectCountByExample(example);
        log.info("收藏总条数:{}", totalSize);
        //总页数
        int totalPage = totalSize % size == 0 ? totalSize / size : totalSize / size + 1;
        log.info("收藏总页数:{}", totalPage);
        if (page <= 0) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        log.info("修正收藏page参数:{}", page);
        List<House> houseList = new ArrayList<>();
        for (Collection collection : collectionList) {
            House house = houseService.findHouseById(collection.getHouseId());
            if (house != null) {
                houseList.add(house);
            }
        }
        for (House house : houseList) {
            String oldTypeImage = house.getTypeImage();
            String newTypeImage = oldTypeImage + "?imageView2/2/w/400/h/200";
            house.setTypeImage(newTypeImage);
        }
        Map<String, Object> res = new HashMap<>();
        res.put("houseList", houseList);
        res.put("currentPage", page);
        return res;
    }

    public void deleteCollection(Long houseId) {
        Example example = new Example(Collection.class);
        example.createCriteria().andEqualTo("houseId", houseId);
        collectionMapper.deleteByExample(example);
    }
}
