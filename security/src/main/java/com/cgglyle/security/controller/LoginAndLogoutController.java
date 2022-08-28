package com.cgglyle.security.controller;

import com.cgglyle.security.query.LoginQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author lyle
 * @since 2022/08/18
 */
@Tag(name = "登录登出控制")
@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class LoginAndLogoutController {
    @Operation(summary = "登录")
    @PostMapping("/login")
    public void login(@RequestBody LoginQuery query){}

    @Operation(summary = "登出")
    @PostMapping("/logout")
    public void logout(){}
}
