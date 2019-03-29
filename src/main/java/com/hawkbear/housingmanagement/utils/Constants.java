package com.hawkbear.housingmanagement.utils;

import com.google.common.base.Joiner;

/**
 * 常量类
 *
 * @author wangshuguang
 * @since 2019/01/28
 */
public class Constants {

    public static final String PROJECT_NAME = "房屋售卖系统";
    public static final String PRINCIPAL_PHONE = "17853146435";

    // Redis 键名称
    public static final String REDIS_PROJECT_INFO = "hm_project_info";
    public static final String REDIS_USER_TOKEN = "hm_user_token";

    // 用户操作
    public static final String REGISTER = "register";
    public static final String LOGIN = "login";
    public static final String LOGOUT = "logout";

    //房屋状态
    public static final Integer HOUSE_NORMAL = 1;       // 正常状态
    public static final Integer HOUSE_DEL = 2;         // 删除
    public static final Integer HOUSE_RENTED =3;        //出租


    //邀请函状态
    public static final Integer INVITATION_SEND = 1;  //已经发送状态 (未读)
    public static final Integer INVITATION_READ = 2;  //已读状态


    public static final String Authorization = "Authorization";
    
    private static final String SPLIT = ":";
    
    private static final Joiner JOINER = Joiner.on(SPLIT);
    
    public static String getRedisKey(String... keys) {
        return JOINER.join(keys);
    }
}
