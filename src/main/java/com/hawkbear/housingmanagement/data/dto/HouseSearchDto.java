package com.hawkbear.housingmanagement.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class HouseSearchDto {

    private String address;

    private Long lowPrice;

    private Long highPrice;

    private Long lowArea;

    private Long highArea;

    private String type;
}
