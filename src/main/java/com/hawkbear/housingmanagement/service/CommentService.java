package com.hawkbear.housingmanagement.service;

import com.hawkbear.housingmanagement.data.dto.CommentDto;
import com.hawkbear.housingmanagement.data.dto.User;
import com.hawkbear.housingmanagement.data.pojo.Comment;
import com.hawkbear.housingmanagement.mapper.CommentMapper;
import com.hawkbear.housingmanagement.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Autowired
    private ClientService clientService;

    public void addComment(String content, Long userId, Long houseId) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreateTime(new Date());
        comment.setHouseId(houseId);
        comment.setUpdateTime(new Date());
        comment.setUserId(userId);
        commentMapper.insert(comment);
    }

    /**
     * 根据房子id返回评论列表
     * @param houseId
     * @return
     */
    public List<CommentDto> findCommentByHouseId(Long houseId) {
        Comment condition = new Comment();
        condition.setHouseId(houseId);
        List<Comment> commentList = commentMapper.select(condition);
        List<CommentDto> commentDtoList = new ArrayList<>(commentList.size());
        for (Comment comment : commentList){
            User user = clientService.getUser(comment.getUserId());
            CommentDto commentDto = new CommentDto();
            //TODO 用户头像
            commentDto.setUserProfile("../../img/test_house.jpg");
            commentDto.setComment(comment);
            commentDto.setNickname(user.getNickname());
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }
}
