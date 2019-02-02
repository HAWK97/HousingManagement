package com.hawkbear.housingmanagement.service;

import com.alibaba.fastjson.JSONObject;
import com.hawkbear.housingmanagement.client.UserCenterClient;
import com.hawkbear.housingmanagement.data.dto.ProjectInfo;
import com.hawkbear.housingmanagement.data.dto.User;
import com.hawkbear.housingmanagement.data.vo.ResponseMessage;
import com.hawkbear.housingmanagement.exception.MyException;
import com.hawkbear.housingmanagement.holder.ProjectHolder;
import com.hawkbear.housingmanagement.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ClientService
 *
 * @author wangshuguang
 * @since 2019/01/28
 */
@Slf4j
@Service
public class ClientService {

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate template;

    @Resource
    private UserCenterClient client;

    public void genProjectInfo() {
        if (StringUtils.isEmpty(template.opsForValue().get(Constants.REDIS_PROJECT_INFO))) {
            // 缓存中没有项目信息
            ResponseMessage<ProjectInfo> response = client.getProjectInfo(Constants.PROJECT_NAME, Constants.PRINCIPAL_PHONE);
            if (response.isSuccess()) {
                template.opsForValue().set(Constants.REDIS_PROJECT_INFO, JSONObject.toJSONString(response.getData()));
                ProjectHolder.set(response.getData());
            } else {
                throw new MyException(response.getCode(), response.getMsg());
            }
        } else {
            // 存在缓存时直接取出
            ProjectHolder.set(JSONObject.parseObject(template.opsForValue().get(Constants.REDIS_PROJECT_INFO), ProjectInfo.class));
        }
    }

    public ResponseMessage postUser(User user, String type) {
        ProjectInfo projectInfo = ProjectHolder.get();
        if (projectInfo == null) {
            genProjectInfo();
        }
        projectInfo = ProjectHolder.get();
        user.setProjectId(projectInfo.getProjectId());
        ResponseMessage<String> response = new ResponseMessage<>();
        if (Constants.REGISTER.equals(type)) {
            response = client.register(projectInfo.getKey(), user);
        }
        if (response.isSuccess()) {
            template.opsForValue().set(Constants.getRedisKey(Constants.REDIS_USER_TOKEN, response.getData()), JSONObject.toJSONString(user));
        } else {
            throw new MyException(response.getCode(), response.getMsg());
        }
        return response;
    }
}
