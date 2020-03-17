package com.rogear.malltiny.dto;

import com.rogear.malltiny.mbg.model.UmsAdmin;
import com.rogear.malltiny.mbg.model.UmsPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * spring security 的用户详情
 * Created by Rogear on 2020/3/16
 **/
public class AdminUserDetails implements UserDetails {

    private UmsAdmin umsAdmin;
    private List<UmsPermission> umsPermissionList;

    public AdminUserDetails() {
    }

    public AdminUserDetails(UmsAdmin umsAdmin, List<UmsPermission> umsPermissionList) {
        this.umsAdmin = umsAdmin;
        this.umsPermissionList = umsPermissionList;
    }

    /**
     * 返回当前用户的权限
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return umsPermissionList.stream()
                .filter(umsPermission -> umsPermission.getValue() != null)
                .map(umsPermission -> new SimpleGrantedAuthority(umsPermission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return umsAdmin.getStatus().equals(1);
    }
}
