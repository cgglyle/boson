package com.cgglyle.admin.controller;

import com.cgglyle.admin.model.entity.UserEntity;
import com.cgglyle.admin.model.entity.UserPasswdEntity;
import com.cgglyle.admin.query.UserPasswdSaveQuery;
import com.cgglyle.admin.query.UserPasswdUpdateQuery;
import com.cgglyle.admin.query.UserSaveQuery;
import com.cgglyle.admin.service.IUserPasswdService;
import com.cgglyle.admin.vo.UserVo;
import com.cgglyle.logger.annotaion.UnityLog;
import com.cgglyle.logger.enums.LogMethodEnum;
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
    private final com.cgglyle.admin.service.IUserService IUserService;
    private final IUserPasswdService userPasswdService;
    private static final String SECURITY_USER = "安全-用户模块";
    private static final String SECURITY_USER_PASSWD = "安全-用户密码模块";

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

    @ResponseStatus(HttpStatus.CREATED)
    @UnityLog(module = SECURITY_USER_PASSWD, method = LogMethodEnum.SAVE, explain = "添加密码")
    @Operation(summary = "添加密码")
    @PostMapping("/passwd")
    public boolean save(@RequestBody @Valid UserPasswdSaveQuery query, BindingResult bindingResult) {
        UserPasswdEntity entity = new UserPasswdEntity();
        BeanUtils.copyProperties(query, entity);
        return userPasswdService.save(entity);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @UnityLog(module = SECURITY_USER_PASSWD, method = LogMethodEnum.UPDATE, explain = "更改密码")
    @Operation(summary = "更改密码")
    @PutMapping("/passwd")
    public void save(@RequestBody @Valid UserPasswdUpdateQuery query, BindingResult bindingResult) {
        userPasswdService.update(query);
    }
}
