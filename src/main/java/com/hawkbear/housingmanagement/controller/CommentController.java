package com.hawkbear.housingmanagement.controller;

import com.hawkbear.housingmanagement.annotation.LoginRequired;
import com.hawkbear.housingmanagement.data.dto.CommentDto;
import com.hawkbear.housingmanagement.data.vo.ResponseMessage;
import com.hawkbear.housingmanagement.holder.UserHolder;
import com.hawkbear.housingmanagement.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {


    @Autowired
    private CommentService commentService;


    @LoginRequired
    @PostMapping("/addComment")
    public ResponseMessage addComment(String content,String houseId){
       Long userId = UserHolder.get().getId();
       commentService.addComment(content,userId,Long.valueOf(houseId));
       return ResponseMessage.successMessage();
    }

    @LoginRequired
    @GetMapping("/getComment/{houseId}")
    public ResponseMessage getComment(@PathVariable Long houseId){
        List<CommentDto> commentDtoList = commentService.findCommentByHouseId(houseId);
        return  ResponseMessage.successMessage(commentDtoList);
    }

}
