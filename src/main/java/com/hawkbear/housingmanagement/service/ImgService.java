package com.hawkbear.housingmanagement.service;

import com.hawkbear.housingmanagement.data.pojo.Img;
import com.hawkbear.housingmanagement.holder.UserHolder;
import com.hawkbear.housingmanagement.mapper.ImgMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ImgService {

    @Resource
    private ImgMapper imgMapper;

    @Resource
    private QiniuService qiniuService;

    public void addImg(Img img) {
        img.setCreateTime(new Date());
        img.setUpdateTime(new Date());
        imgMapper.insert(img);
    }

    public Img findImgByUserId(Long userId) {
        Example example = new Example(Img.class);
        example.createCriteria().andEqualTo("userId", userId);
        return imgMapper.selectOneByExample(example);
    }

    public List<Img> findImgsByHouseId(Long houseId) {
        Example example = new Example(Img.class);
        example.createCriteria().andEqualTo("houseId", houseId);
        return imgMapper.selectByExample(example);
    }

    public void updateProfile(MultipartFile profile) {
        String imgUrl = qiniuService.upload(profile);
        Long userId = UserHolder.get().getId();
        Example example = new Example(Img.class);
        example.createCriteria().andEqualTo("userId", userId);
        Img img = imgMapper.selectOneByExample(example);
        if (null == img) {
            img = new Img();
            img.setImageUrl(imgUrl);
            img.setUserId(userId);
            img.setCreateTime(new Date());
            img.setUpdateTime(new Date());
            imgMapper.insert(img);
            return;
        }
        img.setImageUrl(imgUrl);
        img.setUpdateTime(new Date());
        imgMapper.updateByPrimaryKey(img);
    }
}
