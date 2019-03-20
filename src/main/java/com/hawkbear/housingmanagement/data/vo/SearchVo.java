package com.hawkbear.housingmanagement.data.vo;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author xch
 * @since 2019/3/20 10:59
 **/
@Setter
@NoArgsConstructor
public class SearchVo {

    //地址
    private String address;

    //价格区间
    private String priceRegion ;

    //面积区间
    private String squraRegion;

    //户型
    private String type;

    //排序类型
    private String order;


    public Integer getLowPrice(){
        if (StringUtils.equals("全部",priceRegion))
            return null;
        return Integer.valueOf(priceRegion.split("-")[0]);
    }

    public Integer getHighPrice(){
        if (StringUtils.equals("全部",priceRegion))
            return null;
        return Integer.valueOf(priceRegion.split("-")[1]);
    }

    public Integer getLowArea(){
        if (StringUtils.equals("全部",squraRegion))
            return null;
        return Integer.valueOf(squraRegion.split("-")[0]);
    }

    public Integer getHighArea(){
        if (StringUtils.equals("全部",squraRegion))
            return null;
        return Integer.valueOf(squraRegion.split("-")[1]);
    }

    public boolean getOrderByArea(){
        return StringUtils.equals(order,"大小");
    }


    public boolean getOrderByPrice(){
        return StringUtils.equals(order,"价格") || StringUtils.equals(order,"默认");
    }

    public String getType(){
        if (StringUtils.equals("全部",type))
            return null;
        return type;
    }

    public String getAddress(){
        if (StringUtils.equals("全部",address))
            return null;
        return address;
    }
}
