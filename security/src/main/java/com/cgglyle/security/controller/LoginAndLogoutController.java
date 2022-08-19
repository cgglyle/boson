package com.cgglyle.security.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyle
 * @since 2022/08/18
 */
@Tag(name = "LoginAndLogoutController", description = "登录登出控制器")
@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class LoginAndLogoutController {
    private static final String LOGIN = "登录模块";
    @PostMapping("/login")
    public void login(String username, String password){
    }

    @PostMapping("/logout")
    public void logout(){};
}
