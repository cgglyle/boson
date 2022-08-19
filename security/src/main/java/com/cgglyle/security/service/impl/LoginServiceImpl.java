package com.cgglyle.security.service.impl;

import com.cgglyle.common.model.UserInfo;
import com.cgglyle.common.unity.status.ClientErrorCode;
import com.cgglyle.security.model.entity.UserEntity;
import com.cgglyle.security.model.entity.UserPasswdEntity;
import com.cgglyle.security.model.entity.UserRoleRelationEntity;
import com.cgglyle.security.service.ILoginService;
import com.cgglyle.security.service.IUserPasswdService;
import com.cgglyle.security.service.IUserRoleRelationService;
import com.cgglyle.security.service.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author lyle
 * @since 2022/08/18
 */
@Service
public class LoginServiceImpl implements ILoginService {
    private final IUserRoleRelationService userRoleRelationService;
    private final IUserPasswdService userPasswdService;
    private final IUserService userService;

    public LoginServiceImpl(IUserRoleRelationService userRoleRelationService, IUserPasswdService userPasswdService, IUserService userService) {
        this.userRoleRelationService = userRoleRelationService;
        this.userPasswdService = userPasswdService;
        this.userService = userService;
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userService.getByUserName(username);
        if (entity.getId() == null){
            throw new UsernameNotFoundException(ClientErrorCode.USERNAME_NOTFOUND.getMsg());
        }
        UserRoleRelationEntity userRoleRelationEntity = userRoleRelationService.getByUserId(entity.getId());
        UserPasswdEntity userPasswdEntity = userPasswdService.getByUserId(entity.getId());
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(entity.getId());
        userInfo.setUsername(entity.getNickname());
        userInfo.setPassword(userPasswdEntity.getUserPasswd());
        userInfo.setRoleId(userRoleRelationEntity.getRoleId());
        userInfo.setAccountNonLocked(!entity.getIsStatus());
        userInfo.setEnabled(!entity.getIsStatus());
        userInfo.setCredentialsNonExpired(!userPasswdEntity.getIsStatus());
        return userInfo;
    }
}
