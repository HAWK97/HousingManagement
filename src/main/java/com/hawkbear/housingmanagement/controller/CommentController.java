package com.hawkbear.housingmanagement.controller;

import com.github.pagehelper.PageInfo;
import com.hawkbear.housingmanagement.annotation.LoginRequired;
import com.hawkbear.housingmanagement.data.pojo.Comment;
import com.hawkbear.housingmanagement.data.vo.CommentVo;
import com.hawkbear.housingmanagement.data.vo.ResponseMessage;
import com.hawkbear.housingmanagement.holder.UserHolder;
import com.hawkbear.housingmanagement.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @ApiOperation(value = "新增评论", notes = "需要传入评论内容与房屋id")
    @PostMapping
    @LoginRequired
    public ResponseMessage addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return ResponseMessage.successMessage();
    }

    @ApiOperation(value = "获取评论列表", notes = "需要传入房屋id，显示页数与每页大小")
    @GetMapping("/{houseId}")
    public ResponseMessage findCommentsByHouseId(@PathVariable("houseId") Long houseId,
                                                 @RequestParam(required = false, defaultValue = "1") int page,
                                                 @RequestParam(required = false, defaultValue = "5") int size) {
        Map<String, Object> result = new HashMap<>();
        PageInfo<CommentVo> commentVoPageInfo = commentService.getCommentVo(houseId, page, size);
        result.put("totalPage", commentVoPageInfo.getPages());
        result.put("currentPage", commentVoPageInfo.getPageNum());
        result.put("commentList", commentVoPageInfo.getList());
        return ResponseMessage.successMessage(result);
    }
}
