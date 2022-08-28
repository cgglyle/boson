package com.cgglyle.admin.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开发测试控制器
 *
 * @author lyle
 * @since 2022/08/28
 */

@Tag(name = "开发测试接口", description = "用于开发的测试接口，不部署到生产环境")
@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "/dev")
@RequiredArgsConstructor
public class DevController {

}
