package com.hawkbear.housingmanagement.service;

import com.hawkbear.housingmanagement.data.pojo.Collection;
import com.hawkbear.housingmanagement.mapper.CollectionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CollectionService {

    @Resource
    private CollectionMapper collectionMapper;

    public int addCollection(Long userId, Long houseId) {
        Collection collection = new Collection();
        collection.setHouseId(houseId);
        collection.setCreateTime(new Date());
        collection.setUpdateTime(new Date());
        collection.setUserId(userId);
        return collectionMapper.insert(collection);
    }
}
