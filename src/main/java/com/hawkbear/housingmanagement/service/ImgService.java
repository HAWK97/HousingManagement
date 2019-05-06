package com.hawkbear.housingmanagement.service;

import com.hawkbear.housingmanagement.data.pojo.Img;
import com.hawkbear.housingmanagement.mapper.ImgMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ImgService {

    @Resource
    private ImgMapper imgMapper;

    public void addImg(Img img) {
        img.setCreateTime(new Date());
        img.setUpdateTime(new Date());
        imgMapper.insert(img);
    }
}
