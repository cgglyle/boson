package com.cgglyle.admin.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lyle
 * @since 2022-08-17
 */
@Tag(name = "UserPasswdEntity")
@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserPasswdController {

}
