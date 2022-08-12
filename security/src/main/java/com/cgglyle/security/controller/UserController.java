package com.cgglyle.security.controller;

import com.cgglyle.security.model.UserEntity;
import com.cgglyle.security.service.UserService;
import com.cgglyle.security.vo.UserSaveVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author lylecgg
 */
@Tag(name = "user")
@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "查看用户")
    @GetMapping("/user")
    public List<UserEntity> list() {
        return userService.list();
    }

    @Operation(summary = "添加用户")
    @PostMapping("/user")
    public boolean save(@RequestBody @Valid UserSaveVo vo) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(vo, userEntity);
        return userService.save(userEntity);
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/user")
    public boolean remove(Long id) {
        return userService.removeById(id);
    }

    @Operation(summary = "查询总数")
    @GetMapping("/count")
    public long count() {
        return userService.count();
    }
}
