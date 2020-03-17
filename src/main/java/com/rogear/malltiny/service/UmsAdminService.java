package com.rogear.malltiny.service;

import com.rogear.malltiny.mbg.model.UmsAdmin;
import com.rogear.malltiny.mbg.model.UmsPermission;

import java.util.List;

/**
 * 后台管理员Service
 * Created by Rogear on 2020/3/16
 **/
public interface UmsAdminService {

    /**
     * 根据用户名获取后台管理员
     * @param username
     * @return
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 后台管理员注册
     * @param umsAdmin
     * @return
     */
    UmsAdmin register(UmsAdmin umsAdmin);

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    String login(String username,String password);

    /**
     * 获取用户所有权限（包括角色权限和用户加减权限）
     * @param adminId
     * @return
     */
    List<UmsPermission> getPermissionList(Long adminId);
}
