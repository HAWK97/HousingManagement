package com.hawkbear.housingmanagement.controller;

import com.hawkbear.housingmanagement.annotation.LoginRequired;
import com.hawkbear.housingmanagement.data.dto.ProjectInfo;
import com.hawkbear.housingmanagement.data.dto.User;
import com.hawkbear.housingmanagement.data.vo.ResponseMessage;
import com.hawkbear.housingmanagement.holder.ProjectHolder;
import com.hawkbear.housingmanagement.holder.UserHolder;
import com.hawkbear.housingmanagement.service.ClientService;
import com.hawkbear.housingmanagement.service.ImgService;
import com.hawkbear.housingmanagement.utils.Constants;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private ClientService clientService;

    @Resource
    private ImgService imgService;

    @ApiOperation(value = "用户注册", notes = "需要传入...待写")
    @PostMapping("/register")
    public ResponseMessage register(@RequestBody User user) {
        return clientService.postUser(user, Constants.REGISTER);
    }

    @ApiOperation(value = "用户登录", notes = "需要传入用户名、密码")
    @PostMapping("/login")
    public ResponseMessage login(@RequestBody User loginUser) {
        ProjectInfo projectInfo = ProjectHolder.get();
        loginUser.setProjectId(projectInfo.getProjectId());
        return clientService.postUser(loginUser, Constants.LOGIN);
    }

    @ApiOperation(value = "更新用户信息", notes = "需要传入...待写，特别注意：更新之后返回新的token，需要覆盖localstorage里的旧token")
    @PostMapping("/update")
    public ResponseMessage update(@RequestBody User user, MultipartFile profile) {
        ProjectInfo projectInfo = ProjectHolder.get();
        user.setProjectId(projectInfo.getProjectId());
        if (null != profile) {
            imgService.updateProfile(profile);
        }
        return clientService.postUser(user, Constants.UPDATE);
    }

    @ApiOperation(value = "获取当前用户", notes = "需要登录")
    @GetMapping
    @LoginRequired
    public ResponseMessage getUser() {
        return ResponseMessage.successMessage(UserHolder.get());
    }
}
