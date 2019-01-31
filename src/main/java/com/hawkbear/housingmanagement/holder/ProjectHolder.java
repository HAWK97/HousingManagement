package com.hawkbear.housingmanagement.holder;

import com.hawkbear.housingmanagement.data.dto.ProjectInfo;

/**
 * 存放项目信息
 *
 * @author wangshuguang
 * @since 2019/01/28
 */
public class ProjectHolder {

    private static final ThreadLocal<ProjectInfo> projectThreadLocal = new ThreadLocal<>();

    public static ProjectInfo get() {
        return projectThreadLocal.get();
    }

    public static void set(ProjectInfo projectInfo) {
        projectThreadLocal.set(projectInfo);
    }

    public static void remove() {
        projectThreadLocal.remove();
    }
}
