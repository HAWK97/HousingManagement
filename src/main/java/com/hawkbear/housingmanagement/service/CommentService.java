package com.hawkbear.housingmanagement.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hawkbear.housingmanagement.data.dto.User;
import com.hawkbear.housingmanagement.data.pojo.Comment;
import com.hawkbear.housingmanagement.data.pojo.Img;
import com.hawkbear.housingmanagement.data.vo.CommentVo;
import com.hawkbear.housingmanagement.holder.UserHolder;
import com.hawkbear.housingmanagement.mapper.CommentMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private ClientService clientService;

    @Resource
    private ImgService imgService;

    public void addComment(String content,Long houseId) {
        Comment  comment = new Comment();
        comment.setContent(content);
        comment.setHouseId(houseId);
        comment.setCreateTime(new Date());
        comment.setUpdateTime(new Date());
        comment.setUserId(UserHolder.get().getId());
        commentMapper.insert(comment);
    }


    public PageInfo<CommentVo> getCommentVo(Long houseId, int page, int size){
        Example example = new Example(Comment.class);
        example.createCriteria().andEqualTo("houseId", houseId);
        //总条数
        int totalSize = commentMapper.selectCountByExample(example);
        //总页数
        int totalPage = totalSize%size == 0 ? totalSize/size : totalSize/size + 1;
        if (page <= 0 ){
            page = 1;
        }
        if (page > totalPage){
            page = totalPage;
        }
        return getCommentVoHelper(houseId, page, size);
    }


    public PageInfo<CommentVo> getCommentVoHelper(Long houseId, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = new Example(Comment.class);
        example.createCriteria().andEqualTo("houseId", houseId);
        List<Comment> commentList = commentMapper.selectByExample(example);
        List<CommentVo> commentVoList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentVo commentVo = new CommentVo();
            commentVo.setContent(comment.getContent());
            commentVo.setDate(comment.getCreateTime());
            User user = clientService.getUser(comment.getUserId());
            commentVo.setUsername(user.getNickname());
            Img profile = imgService.findImgByUserId(user.getId());
            if (null == profile) {
                commentVo.setProfile("http://cdn.stalary.com/2e8512a721.png");
            } else {
                commentVo.setProfile(profile.getImageUrl());
            }
            commentVoList.add(commentVo);
        }
        return new PageInfo<>(commentVoList);
    }
}
