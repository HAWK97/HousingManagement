package com.hawkbear.housingmanagement.data.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class CommentVo {

    private String content;

    private String username;

    private String profile;

    private Date date;

}
