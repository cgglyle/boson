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

package com.cgglyle.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.cgglyle.common.model.BaseEntity;
import com.cgglyle.common.service.BaseService;
import com.cgglyle.common.service.impl.BaseServiceImpl;

import java.util.Collections;

/**
 * MybatisPlus 代码生成器
 * @author lyle
 * @date 2022/08/14
 */
public class MybatisPlusGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/boson?allowMultiQueries=true&useUnicode=true&nullCatalogMeansCurrent=true&characterEncoding=UTF-8",
                        "root", "123456")
                .globalConfig(builder -> {
                    builder.author("lyle")
                            .enableSpringdoc()
                            .outputDir("/home/lyle/IdeaProjects/boson/temp");
                })
                .packageConfig(builder -> {
                    builder.parent("com.cgglyle")
                            .moduleName("security")
                            .entity("module")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "/home/lyle/IdeaProjects/boson/temp"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("security_role_inheritance")
                            .addTablePrefix("security_");
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder()
                            .superClass(BaseEntity.class)
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .serviceBuilder()
                            .superServiceClass(BaseService.class)
                            .superServiceImplClass(BaseServiceImpl.class)
                            .mapperBuilder()
                            .controllerBuilder()
                            .enableRestStyle()
                            .build();
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
