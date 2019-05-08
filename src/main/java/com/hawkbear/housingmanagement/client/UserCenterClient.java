package com.hawkbear.housingmanagement.client;

import com.hawkbear.housingmanagement.data.dto.ProjectInfo;
import com.hawkbear.housingmanagement.data.dto.User;
import com.hawkbear.housingmanagement.data.vo.ResponseMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户中心调用客户端
 *
 * @author wangshuguang
 * @since 2019/01/28
 */
@FeignClient(name = "user", url = "${server.user}")
public interface UserCenterClient {

    /**
     * 获取项目信息
     *
     * @param name 项目名称
     * @param phone 负责人手机
     * @return 项目信息
     */
    @GetMapping("/facade/project")
    ResponseMessage<ProjectInfo> getProjectInfo(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "phone") String phone);

    /**
     * 用户注册
     *
     * @param key 项目密钥
     * @param user 用户信息
     * @return 用户 token
     */
    @PostMapping("/token/register")
    ResponseMessage<String> register(
            @RequestParam(name = "key") String key,
            @RequestBody User user);

    /**
     * 用户登录
     *
     * @param key 项目密钥
     * @param user 用户信息
     * @return 用户 token
     */
    @PostMapping("/token/login")
    ResponseMessage<String> login(
            @RequestParam(name = "key") String key,
            @RequestBody User user);

    /**
     * 更新用户信息
     *
     * @param key 项目密钥
     * @param user 用户信息
     * @return 用户 token
     */
    @PostMapping("/token/update")
    ResponseMessage<String> updateInfo(
            @RequestParam(name = "key") String key,
            @RequestBody User user);

    /**
     * 获取用户信息
     *
     * @param token 用户 token
     * @param key 项目密钥
     * @return 用户信息
     */
    @GetMapping("/facade/token")
    ResponseMessage<User> getUserInfo(
            @RequestParam(name = "token") String token,
            @RequestParam(name = "key") String key);

    /**
     * @method getUserInfoById 通过用户 id 获取用户信息
     * @param userId 用户 id
     * @param key 项目密钥
     * @param projectId 项目 id
     * @return
     **/
    @GetMapping("/facade/user")
    ResponseMessage<User> getUserInfoById(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "key") String key,
            @RequestParam(name = "projectId") Long projectId);
}
