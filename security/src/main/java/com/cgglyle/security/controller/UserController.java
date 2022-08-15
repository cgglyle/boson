package com.cgglyle.security.controller;

import com.cgglyle.logger.annotaion.UnityLog;
import com.cgglyle.logger.enums.LogMethodEnum;
import com.cgglyle.security.model.entity.UserEntity;
import com.cgglyle.security.query.UserSaveQuery;
import com.cgglyle.security.service.IUserService;
import com.cgglyle.security.vo.UserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author lylecgg
 */
@Tag(name = "User")
@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class UserController {
    private final IUserService IUserService;

    private static final String SECURITY_USER = "安全-用户模块";

    @UnityLog(module = SECURITY_USER, method = LogMethodEnum.SEARCH, explain = "查找用户")
    @Operation(summary = "查看用户")
    @GetMapping("/users")
    public List<UserEntity> list() {
        return IUserService.list();
    }

    @UnityLog(module = SECURITY_USER, method = LogMethodEnum.SEARCH, explain = "根据ID查找用户")
    @Operation(summary = "根据ID查找用户")
    @GetMapping("/users/{id}")
    public UserVo getById(@PathVariable(value = "id") Long id){
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(IUserService.getById(id),userVo);
        return userVo;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @UnityLog(module = SECURITY_USER, method = LogMethodEnum.SAVE, explain = "添加用户")
    @Operation(summary = "添加用户")
    @PostMapping("/users")
    public boolean save(@RequestBody @Valid UserSaveQuery vo, BindingResult bindingResult) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(vo, userEntity);
        return IUserService.save(userEntity);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @UnityLog(module = SECURITY_USER, method = LogMethodEnum.DELETED, explain = "删除用户")
    @Operation(summary = "删除用户")
    @DeleteMapping("/users")
    public void remove(Long id) {
        IUserService.removeById(id);
    }

    @UnityLog(module = SECURITY_USER, method = LogMethodEnum.SEARCH, explain = "查询总数")
    @Operation(summary = "查询总数")
    @GetMapping("/users/counts")
    public long count() {
        return IUserService.count();
    }
}
