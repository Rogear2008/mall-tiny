package com.rogear.malltiny.controller;

import com.rogear.malltiny.common.api.CommonResult;
import com.rogear.malltiny.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 会员注册登录controller
 * Created by Rogear on 2020/3/13
 **/
@Api(tags = "UmsMemberController",description = "会员登录注册管理")
@RestController
@RequestMapping("/sso")
public class UmsMemberController {

    @Autowired
    private UmsMemberService umsMemberService;

    Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @ApiOperation("根据电话号码生成验证码")
    @GetMapping("/getAuthCode")
    public CommonResult getAuthCode(@RequestParam String telephone){
        CommonResult commonResult = umsMemberService.generateAuthCode(telephone);
        LOGGER.info("getAuthCode:{}",null == commonResult.getData()?null:commonResult.getData().toString());
        return commonResult;
    };

    @ApiOperation("判断验证码和手机号是否匹配")
    @PostMapping("/verifyAuthCode")
    public CommonResult verifyAuthCode(@RequestParam String telephone,@RequestParam String authCode){
        CommonResult commonResult = umsMemberService.verifyAuthCode(telephone, authCode);
        LOGGER.info("verifyAuthCode:{}",null == commonResult.getData()?null:commonResult.getData().toString());
        return commonResult;
    };
}
