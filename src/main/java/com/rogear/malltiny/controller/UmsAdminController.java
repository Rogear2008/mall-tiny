package com.rogear.malltiny.controller;

import com.rogear.malltiny.common.api.CommonResult;
import com.rogear.malltiny.dto.UmsAdminLoginParam;
import com.rogear.malltiny.mbg.model.UmsAdmin;
import com.rogear.malltiny.mbg.model.UmsPermission;
import com.rogear.malltiny.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.security.Permission;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台用户管理
 * Created by Rogear on 2020/3/16
 **/
@Api(tags = "UmsAdminController",description = "后台用户管理")
@RestController
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService umsAdminService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    @ResponseBody
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdmin){
        UmsAdmin result = umsAdminService.register(umsAdmin);
        if (null == result){
            return CommonResult.faild();
        } else {
            return CommonResult.success(result);
        }
    }

    @ApiOperation(value = "登陆以后返回token")
    @PostMapping("/login")
    @ResponseBody
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam){
        String token = umsAdminService.login(umsAdminLoginParam.getUsername(),umsAdminLoginParam.getPassword());
        if (token == null){
            return CommonResult.validateFaild("用户名或者密码错误");
        }
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "获取用户的权限")
    @GetMapping("/getPermissionList/{adminId}")
    @ResponseBody
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId){
        List<UmsPermission> permissionList = umsAdminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }
}
