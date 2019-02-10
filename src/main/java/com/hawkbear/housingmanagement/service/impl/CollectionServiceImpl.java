package com.hawkbear.housingmanagement.service.impl;

import com.hawkbear.housingmanagement.data.pojo.Collection;
import com.hawkbear.housingmanagement.mapper.CollectionMapper;
import com.hawkbear.housingmanagement.service.CollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Resource
    private CollectionMapper collectionMapper;


    @Override
    public int addCollection(Long userId, Long houseId) {
        Collection collection = new Collection();
        collection.setHouseId(houseId);
        collection.setCreateTime(new Date());
        collection.setUpdateTime(new Date());
        collection.setUserId(userId);
        return collectionMapper.insert(collection);
    }
}
