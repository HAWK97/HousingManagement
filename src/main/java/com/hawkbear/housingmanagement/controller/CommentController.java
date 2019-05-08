package com.hawkbear.housingmanagement.controller;

import com.hawkbear.housingmanagement.annotation.LoginRequired;
import com.hawkbear.housingmanagement.data.vo.ResponseMessage;
import com.hawkbear.housingmanagement.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @ApiOperation(value = "新增评论", notes = "需要传入评论内容与房屋id")
    @PostMapping("/add/{houseId}")
    @LoginRequired
    public ResponseMessage addComment(@PathVariable Long houseId,@RequestParam String content) {
        commentService.addComment(content,houseId);
        return ResponseMessage.successMessage();
    }

    @ApiOperation(value = "获取评论列表", notes = "需要传入房屋id，显示页数与每页大小")
    @GetMapping("/{houseId}")
    public ResponseMessage findCommentsByHouseId(@PathVariable("houseId") Long houseId,
                                                 @RequestParam(required = false, defaultValue = "1") int page,
                                                 @RequestParam(required = false, defaultValue = "5") int size) {
        return ResponseMessage.successMessage(commentService.getCommentVo(houseId, page, size));
    }
}
