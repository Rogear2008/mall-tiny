package com.rogear.malltiny.service.impl;

import com.rogear.malltiny.common.api.CommonResult;
import com.rogear.malltiny.service.RedisService;
import com.rogear.malltiny.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * 会员管理实现类
 * Created by Rogear on 2020/3/13
 **/
@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDES;

    @Override
    public CommonResult generateAuthCode(String telephone) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++){
            stringBuilder.append(random.nextInt(10));
        }
        //将验证码和手机号绑定存放到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE+telephone,stringBuilder.toString());
        //设置超时时间
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE+telephone,AUTH_CODE_EXPIRE_SECONDES);
        return CommonResult.success(stringBuilder.toString(),"获取验证码成功");
    }

    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        if (authCode.isEmpty()){
            return CommonResult.faild("请输入验证码");
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE+telephone);
        if (null != realAuthCode && realAuthCode.equals(authCode)){
            return CommonResult.success(null,"验证码校验成功");
        } else {
            return CommonResult.faild("验证码校验失败");
        }
    }
}
