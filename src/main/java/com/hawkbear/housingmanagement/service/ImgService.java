package com.hawkbear.housingmanagement.service;

public interface ImgService {

    void addImgs(String[] imgs,Long houseId);

    void addImg(String imgUrl,Long houseId);
}
