package com.hawkbear.housingmanagement.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目信息
 *
 * @author wangshuguang
 * @since 2019/01/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectInfo {

    private Long projectId;

    private String key;
}
