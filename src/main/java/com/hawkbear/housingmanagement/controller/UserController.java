package com.hawkbear.housingmanagement.controller;

import com.hawkbear.housingmanagement.data.dto.User;
import com.hawkbear.housingmanagement.data.vo.ResponseMessage;
import com.hawkbear.housingmanagement.service.ClientService;
import com.hawkbear.housingmanagement.utils.Constants;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private ClientService clientService;

    @PostMapping("/register")
    public ResponseMessage register(@RequestBody User user) {
        return clientService.postUser(user, Constants.REGISTER);
    }
}
