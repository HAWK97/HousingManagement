package com.hawkbear.housingmanagement.service.impl;

import com.hawkbear.housingmanagement.data.pojo.Img;
import com.hawkbear.housingmanagement.mapper.ImgMapper;
import com.hawkbear.housingmanagement.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author xch
 * @since 2019/3/29 16:12
 **/
@Service
public class ImgServiceImpl implements ImgService {

    @Autowired
    private ImgMapper imgMapper;

    @Override
    public void addImgs(String[] imgs, Long houseId) {
        for (String img : imgs){
            addImg(img,houseId);
        }
    }

    @Override
    public void addImg(String imgUrl, Long houseId) {
        Img img = new Img();
        img.setCreateTime(new Date());
        img.setHouseId(houseId);
        img.setImageUrl(imgUrl);
        img.setStatus(0);
        img.setUpdateTime(new Date());
        imgMapper.insert(img);
    }
}
