package com.hawkbear.housingmanagement.service;


public interface CollectionService {

    /**
     * 添加收藏
     * @param userId
     * @param houseId
     * @return
     */
    int addCollection(Long userId,Long houseId);
}
