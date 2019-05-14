package com.hawkbear.housingmanagement.data.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class InvitationVo {

    private Long houseId;

    private String introduce;

    private String address;

    private String type;

    private Long price;

    private String buyer;

    private String buyerPhone;

    private String seller;

    private String sellerPhone;
}
