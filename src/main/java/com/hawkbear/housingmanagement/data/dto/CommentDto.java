package com.hawkbear.housingmanagement.data.dto;

import com.hawkbear.housingmanagement.data.pojo.Comment;
import lombok.Data;

/**
 * @author xch
 * @since 2019/3/29 11:41
 **/
@Data
public class CommentDto {

    private Comment comment;

    private String nickname;       //用户姓名

}
