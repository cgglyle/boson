package com.cgglyle.security.config;

import com.cgglyle.security.filter.LoginFilter;
import com.cgglyle.security.handler.LoginEntryPointHandler;
import com.cgglyle.security.handler.LoginFailureHandler;
import com.cgglyle.security.handler.LoginSuccessHandler;
import com.cgglyle.security.handler.LogoutHandler;
import com.cgglyle.security.service.ILoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
public class SecurityConfig {
    private final ILoginService loginService;
    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailureHandler loginFailureHandler;
    private final LoginEntryPointHandler loginEntryPointHandler;
    private final LogoutHandler logoutHandler;

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
    public LoginFilter loginFilter(HttpSecurity httpSecurity) throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        // 登录成功处理器
        loginFilter.setAuthenticationSuccessHandler(loginSuccessHandler);
        // 登录失败处理器
        loginFilter.setAuthenticationFailureHandler(loginFailureHandler);
        // 注入认证管理器 SpringSecurity 5.7 方式（可能有待改进）
        loginFilter.setAuthenticationManager(authenticationManagerBean(httpSecurity.getSharedObject(AuthenticationConfiguration.class)));
        loginFilter.setFilterProcessesUrl("/login");
        return loginFilter;
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
        return httpSecurity.authorizeRequests(authorize -> authorize
                        .antMatchers("/v3/api-docs/**").permitAll()
                        .antMatchers("/doc.html/**").permitAll()
                        .antMatchers("/swagger-ui/**").permitAll()
                        .antMatchers("/webjars/**").permitAll()
                        .anyRequest().authenticated()
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
                .and()
                .build();
    }
}
