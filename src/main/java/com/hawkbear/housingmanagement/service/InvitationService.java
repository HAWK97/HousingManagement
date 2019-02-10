package com.hawkbear.housingmanagement.service;

public interface InvitationService {
    /**
     *
     * @param userId 用户id
     * @param sellerId 出售房屋人id
     * @param houseId 房屋id
     * @param time 邀请时间
     */
    int  sendInvitation(Long userId, Long sellerId, Long houseId, String time);
}
