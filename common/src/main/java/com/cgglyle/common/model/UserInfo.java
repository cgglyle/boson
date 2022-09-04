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
