/*
 * Copyright 2022 Cgglyle
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
