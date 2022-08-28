package com.cgglyle.security.service.impl;

import com.cgglyle.security.event.DynamicAuthorizationChangeEvent;
import com.cgglyle.security.service.DynamicAuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 动态权限
 *
 * @author lyle
 * @since 2022/08/21
 */
@Service
@RequiredArgsConstructor
public class DynamicAuthorization implements AuthorizationManager<RequestAuthorizationContext> {
    private final DynamicAuthorizationService dynamicAuthorizationService;
    private static final PathMatcher matcher = new AntPathMatcher();
    private static Boolean isAnonymousUser;
    private static Map<String, List<Long>> permissionRoleMap;

    /**
     * 初始化动态权限系统
     * <p>
     * 监听{@link ContextRefreshedEvent}事件
     * Order顺序越小越先执行
     */
    @Order(0)
    @EventListener(ContextRefreshedEvent.class)
    public void initialization(){
        permissionRoleMap = dynamicAuthorizationService.permissionList();
        isAnonymousUser = dynamicAuthorizationService.isAnonymousUser();
    }

    // 监听权限更新
    @EventListener(DynamicAuthorizationChangeEvent.class)
    public void printLog() {
        permissionRoleMap = dynamicAuthorizationService.permissionList();
    }

    /**
     * Determines if access is granted for a specific authentication and object.
     *
     * @param authentication the {@link Supplier} of the {@link Authentication} to check
     * @param object         the {@link RequestAuthorizationContext} object to check
     * @return an {@link AuthorizationDecision} or null if no decision could be made
     */
    @Override
    public AuthorizationDecision check(Supplier authentication, RequestAuthorizationContext object) {
        Object o = authentication.get();
        // 获取token
        AbstractAuthenticationToken token = (AbstractAuthenticationToken) o;
        // 获取认证信息
        Collection<GrantedAuthority> authorities = token.getAuthorities();
        // 获取权限列表
        List<GrantedAuthority> collect = authorities.stream().toList();
        // 获取零号权限
        GrantedAuthority grantedAuthority = collect.get(0);
        // 获取权限实体
        String authority = grantedAuthority.getAuthority();
        // 获取请求地址
        HttpServletRequest request = object.getRequest();
        String requestUrl = request.getRequestURI();
        // 获取请求方式
        String method = request.getMethod();
        // 拼接
        StringBuilder buffer = new StringBuilder();
        buffer.append(method);
        buffer.append(requestUrl);
        // 请求url是否动态 /users/{id}
        if (permissionRoleMap.containsKey(buffer.toString())){
            List<Long> longs = permissionRoleMap.get(buffer.toString());
            // 如果是USER权限或者ANONYMOUS权限直接放行
            return verify(longs, authority);
        }
        // 去permissionMap中搜索权限ID
        List<Long> roleInheritanceList = null;
        for (Map.Entry<String, List<Long>> entry : permissionRoleMap.entrySet()){
            if (matcher.match(entry.getKey(), buffer.toString())){
                roleInheritanceList = entry.getValue();
            }
        }
        // 如果没有找到PermissionId就是库中还没有设置权限，现阶段默认直接通过
        // FIXME：因为现阶段没有全部导入所有地址，所以暂时设置为没有定义权限的接口默认都是匿名即可通过
        if (roleInheritanceList == null || roleInheritanceList.isEmpty()){
            return new AuthorizationDecision(true);
        }
        return verify(roleInheritanceList, authority);
    }

    /**
     * 判断用户是否拥有角色
     * <p>
     * 如果地址需要<code>anonymous</code>权限，在开启<code>anonymous</code>权限的情况下会直接放行。
     * 如果角色拥有<code>administrator</code>权限也会直接放行。
     *
     * @param roleInheritanceList url需要的角色列表
     * @param authority 用户角色
     * @return 授权模型
     */
    private AuthorizationDecision verify(List<Long> roleInheritanceList, String authority){
        if (!authority.equals("ROLE_ANONYMOUS")){
            if (Long.valueOf(authority).equals(1L)){
                return new AuthorizationDecision(true);
            }
        }
        if (isAnonymousUser){
            if (roleInheritanceList.stream().anyMatch(id -> id.equals(3L) || id.toString().equals(authority))) {
                return new AuthorizationDecision(true);
            }
        }
        if (roleInheritanceList.stream().anyMatch(id -> id.toString().equals(authority))) {
            return new AuthorizationDecision(true);
        }
        return new AuthorizationDecision(false);
    }
}
