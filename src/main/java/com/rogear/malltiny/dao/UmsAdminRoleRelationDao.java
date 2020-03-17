package com.rogear.malltiny.dao;

import com.rogear.malltiny.mbg.model.UmsPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户与角色管理自定义dao
 * Created by Rogear on 2020/3/16
 **/
public interface UmsAdminRoleRelationDao {

    /**
     * 获取用户所有权限
     * @param id
     * @return
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long id);
}
