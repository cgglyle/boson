package com.cgglyle.security.config;

import com.cgglyle.security.filter.LoginFilters;
import com.cgglyle.security.handler.*;
import com.cgglyle.security.service.ILoginService;
import com.cgglyle.security.service.impl.DynamicPermissionAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SpringSecurity 配置类
 *
 * @author lyle
 * @since 2022/08/17
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final ILoginService loginService;
    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailureHandler loginFailureHandler;
    private final LoginEntryPointHandler loginEntryPointHandler;
    private final LoginAccessDeniedHandler loginAccessDeniedHandler;
    private final LogoutHandler logoutHandler;
    private final DynamicPermissionAuthentication dynamicPermissionAuthentication;

    /**
     * 加密模式
     */
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(10);
    }

    /**
     * 自定义登录拦截器
     */
    @Bean
    public LoginFilters loginFilter(HttpSecurity httpSecurity) throws Exception {
        LoginFilters loginFilters = new LoginFilters();
        // 登录成功处理器
        loginFilters.setAuthenticationSuccessHandler(loginSuccessHandler);
        // 登录失败处理器
        loginFilters.setAuthenticationFailureHandler(loginFailureHandler);
        // 注入认证管理器 SpringSecurity 5.7 方式（可能有待改进）
        loginFilters.setAuthenticationManager(authenticationManagerBean(httpSecurity.getSharedObject(AuthenticationConfiguration.class)));
        loginFilters.setFilterProcessesUrl("/login");
        return loginFilters;
    }

    /**
     * 认证管理器
     * <p>
     * 目的是为了方便给自定义登录拦截器注入
     */
    @Bean
    AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * 设置如何进行身份验证
     */
    @Bean
    AuthenticationProvider provider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // 使用自定义身份验证器
        daoAuthenticationProvider.setUserDetailsService(loginService);
        // 加密模式
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/v3/api-docs/**").permitAll()
                        .antMatchers("/doc.html/**").permitAll()
                        .antMatchers("/swagger-ui/**").permitAll()
                        .antMatchers("/webjars/**").permitAll()
                        .antMatchers("/null/cp/error").permitAll()
                        .anyRequest()
                        .access(dynamicPermissionAuthentication)
                )
                .addFilterAt(loginFilter(httpSecurity), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .and()
                .logout()
                .permitAll()
                .logoutSuccessHandler(logoutHandler)
                .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(loginEntryPointHandler)
                .accessDeniedHandler(loginAccessDeniedHandler)
                .and()
                .build();
    }
}
