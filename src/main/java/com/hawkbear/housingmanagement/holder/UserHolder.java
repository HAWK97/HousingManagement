package com.hawkbear.housingmanagement.holder;

import com.hawkbear.housingmanagement.data.dto.User;

/**
 * 存放用户信息
 *
 * @author wangshuguang
 * @since 2019/01/28
 */
public class UserHolder {

    private static final ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static User get() {
        return userThreadLocal.get();
    }

    public static void set(User userInfo) {
        userThreadLocal.set(userInfo);
    }

    public static void remove() {
        userThreadLocal.remove();
    }
}
