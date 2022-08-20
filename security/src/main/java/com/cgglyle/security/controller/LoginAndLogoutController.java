package com.cgglyle.security.controller;

import com.cgglyle.security.query.LoginQuery;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/login")
    public void login(@RequestBody LoginQuery query){}

    @PostMapping("/logout")
    public void logout(){}
}
