package com.cgglyle.logger.annotaion;

import com.cgglyle.logger.enums.LogMethodEnum;
import com.cgglyle.logger.enums.LogModuleEnum;

import java.lang.annotation.*;

/**
 * 日志介入注解
 * <p></p>
 * 需要打印详细日志的方法使用<p>
 * <code>module</code>放入{@link LogMethodEnum} 模块枚举类<p>
 * <code>method</code>放入{@link LogMethodEnum} 类型枚举类<p>
 * <code>explain</code>写入详细信息<p>
 * 使用此注解后，当调用方法后会自动出现以下日志<p>
 * <pre>例如：
 *     {@snippet :
 *          @UnityLog(module = LogModuleEnum.SECURITY_USER,
 *                      method = LogMethodEnum.SEARCH,
 *                      explain = "查找用户")
 *          @GetMapping("/user")
 *          public List<UserEntity> list() {
 *              return userService.list();
 *          }
 *     }
 *     日志：<p>
 *     {@code (业务日志) [module]=安全-用户模块 [method]=存储 [explain]=添加用户 [url]=http://localhost:8080/user/user [uri]=/user/user [className]=com.cgglyle.security.controller.UserController.save [入参]=[UserSaveVo(nickname=17, email=17@boson.com, phone=17) [出参]=true [耗时]=43ms}
 * </pre>
 *
 * @author lyle
 * @date 2022/08/13
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UnityLog {
    /**
     * 模块
     */
    LogModuleEnum module();

    /**
     * 方法
     */
    LogMethodEnum method();

    /**
     * 解释
     */
    String explain();
}
