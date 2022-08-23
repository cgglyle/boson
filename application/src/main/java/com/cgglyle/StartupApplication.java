package com.cgglyle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动类
 *
 * @author lyle
 */
@Slf4j
@SpringBootApplication
public class StartupApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartupApplication.class, args);
        log.info("Boson 已启动！");
        log.info("BosonAPI 地址: [http://localhost:8080/doc.html");
        log.info("BosonAPI Swagger 地址:[http://localhost:8080/swagger-ui/index.html]");
    }
}
