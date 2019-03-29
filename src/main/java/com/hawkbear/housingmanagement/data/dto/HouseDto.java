package com.hawkbear.housingmanagement.data.dto;

import com.hawkbear.housingmanagement.data.pojo.House;
import lombok.Data;

import java.util.List;

@Data
public class HouseDto {

    private House house;

    private List<String> imgList;

    private String titleImg;

    private String sellerPhoneNum;

    private String sellerName;

    private Long sellerId;
}
