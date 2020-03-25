package com.rogear.malltiny.controller;

import com.rogear.malltiny.common.api.CommonResult;
import com.rogear.malltiny.dto.OssCallbackResult;
import com.rogear.malltiny.dto.OssPolicyResult;
import com.rogear.malltiny.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 阿里云OSS管理
 * Created by Rogear on 2020/3/18
 **/
@Api(tags = "OssController",description = "阿里云OSS管理")
@RestController
@RequestMapping("/aliyun/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation("生成oss上传签名")
    @GetMapping("/policy")
    public CommonResult<OssPolicyResult> policy(){
        OssPolicyResult ossPolicyResult = ossService.policy();
        return CommonResult.success(ossPolicyResult);
    }

    @ApiOperation("oss上传成功回调")
    @PostMapping("/callback")
    public CommonResult<OssCallbackResult> callback(HttpServletRequest request){
        OssCallbackResult callback = ossService.callback(request);
        return CommonResult.success(callback);
    }
}
