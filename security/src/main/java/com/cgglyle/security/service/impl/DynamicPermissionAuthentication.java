package com.cgglyle.security.service.impl;

import com.cgglyle.security.service.IPermissionService;
import com.cgglyle.security.service.IRoleInheritanceService;
import com.cgglyle.security.service.IRolePermissionRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

/**
 * 动态权限
 *
 * @author lyle
 * @since 2022/08/21
 */
@Component
@RequiredArgsConstructor
public class DynamicPermissionAuthentication implements AuthorizationManager<RequestAuthorizationContext> {
    private final IPermissionService permissionService;
    private final IRolePermissionRelationService rolePermissionRelationService;
    private final IRoleInheritanceService roleInheritanceService;
    private Map<String, Long> permissionMap;
    private Map<Long, List<Long>> roleInheritanceMap;
    private static final PathMatcher matcher = new AntPathMatcher();
    /**
     * Determines if access is granted for a specific authentication and object.
     *
     * @param authentication the {@link Supplier} of the {@link Authentication} to check
     * @param object         the {@link RequestAuthorizationContext} object to check
     * @return an {@link AuthorizationDecision} or null if no decision could be made
     */
    @Override
    public AuthorizationDecision check(Supplier authentication, RequestAuthorizationContext object) {
        if (permissionMap == null){
            permissionMap = permissionService.putPermissionToMap();
        }
        if (roleInheritanceMap == null){
            roleInheritanceMap = roleInheritanceService.putRoleInheritanceToMap();
        }
        Object o = authentication.get();
        AbstractAuthenticationToken token = (AbstractAuthenticationToken) o;
        Collection<GrantedAuthority> authorities = token.getAuthorities();
        List<GrantedAuthority> collect = authorities.stream().toList();
        GrantedAuthority grantedAuthority = collect.get(0);
        String authority = grantedAuthority.getAuthority();
        HttpServletRequest request = object.getRequest();
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        StringBuffer buffer = new StringBuffer();
        buffer.append(method);
        buffer.append(requestUrl);
        // 请求url是否动态 /users/{id}
        if (permissionMap.containsKey(buffer.toString())){
            Long permissionId = permissionMap.get(buffer.toString());
            Long roleId = rolePermissionRelationService.getRoleIdByPermissionId(permissionId);
            // 如果是USER权限或者ANONYMOUS权限直接放行
            if (roleId.equals(4L) || roleId.equals(7L)){
                return new AuthorizationDecision(true);
            }
            List<Long> roleList = roleInheritanceMap.get(roleId);
            if (roleList.stream().anyMatch(id -> id.toString().equals(authority))){
                return new AuthorizationDecision(true);
            }
            return new AuthorizationDecision(false);
        }
        // 去permissionMap中搜索权限ID
        AtomicReference<Long> permissionId = new AtomicReference<>();
        permissionMap.forEach((k, v)->{
            if (matcher.match(k, buffer.toString())){
                permissionId.set(v);
            }
        });
        if (permissionId.get().equals(4L) || permissionId.get().equals(7L)){
            return new AuthorizationDecision(true);
        }
        Long roleId = rolePermissionRelationService.getRoleIdByPermissionId(permissionId.get());
        List<Long> roleList = roleInheritanceMap.get(roleId);
        if (roleList.stream().anyMatch(id -> id.toString().equals(authority))){
            return new AuthorizationDecision(true);
        }
        return new AuthorizationDecision(false);
    }
}
