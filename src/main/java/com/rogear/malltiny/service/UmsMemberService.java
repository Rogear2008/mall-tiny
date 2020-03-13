package com.rogear.malltiny.service;

import com.rogear.malltiny.common.api.CommonResult;

/**
 * 会员管理Service
 * Created by Rogear on 2020/3/13
 **/
public interface UmsMemberService {

    /**
     * 根据电话号码生成验证码
     * @param telephone
     * @return
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号是否匹配
     * @param telephone
     * @param authCode
     * @return
     */
    CommonResult verifyAuthCode(String telephone,String authCode);
}
