package com.hawkbear.housingmanagement.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 模糊搜索 vo
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchDto {

    private Integer lowPrice;

    private Integer highPrice;

    //房子户型
    private String type;

    private Integer lowArea;

    private Integer highArea;

    //地址
    private String address;

}
