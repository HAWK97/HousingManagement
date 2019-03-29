package com.hawkbear.housingmanagement.service;

import com.hawkbear.housingmanagement.data.dto.CommentDto;

import java.util.List;

public interface CommentService {

    /**
     * 添加评论
     * @param content
     * @param userId
     * @param houseId
     */
    void addComment(String content,Long userId,Long houseId);


    List<CommentDto> findCommentByHouseId(Long houseId);
}
