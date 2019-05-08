package com.hawkbear.housingmanagement.data.vo;

import com.hawkbear.housingmanagement.data.dto.User;
import com.hawkbear.housingmanagement.data.pojo.House;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class HouseVo {

    private House house;

    private User user;

    private String profile;

    private List<String> imgList;
}
