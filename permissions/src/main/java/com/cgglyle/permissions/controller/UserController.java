package com.cgglyle.permissions.controller;

import com.cgglyle.permissions.model.UserEntity;
import com.cgglyle.permissions.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lylecgg
 */
@Tag(name = "user")
@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "给用户添加角色")
    @GetMapping("/list")
    public List<UserEntity> list() {
        return userService.list();
    }

    @Operation(summary = "查询总数")
    @GetMapping("/count")
    public long count() {
        return userService.count();
    }
}
