package com.cgglyle.common.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author lyle
 * @since 2022/08/18
 */
@Data
public class UserInfo implements UserDetails {
    private Long userId;
    private String username;
    private String password;
    private Long roleId;
    /**
     * 帐号是否锁定，未锁定ture，锁定为false
     */
    private boolean isAccountNonLocked;
    /**
     * 密码是否过期，未过期ture，过期为false
     */
    private boolean isCredentialsNonExpired;
    /**
     * 帐号是否禁用，未禁用ture，禁用为false
     */
    private boolean isEnabled;
    /**
     * 帐号是否过期，为过期ture，过期false
     */
    private boolean isAccountNonExpired;

    /**
     * Returns the authorities granted to the user. Cannot return <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(roleId.toString()));
        return authorities;
    }
}
