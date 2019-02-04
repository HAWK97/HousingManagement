package com.hawkbear.housingmanagement.data.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 模糊搜索 vo
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchVo {

    private Integer lowPrice;

    private Integer highPrice;

    //房子户型
    private String type;

    private Integer lowArea;

    private Integer highArea;

    //false为升序，true为降序
    private boolean orderByArea;

    //false为升序，true为降序
    private boolean orderByPrice;


}
