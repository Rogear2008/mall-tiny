package com.rogear.malltiny.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.rogear.malltiny.common.utils.JwtTokenUtil;
import com.rogear.malltiny.dao.UmsAdminRoleRelationDao;
import com.rogear.malltiny.mbg.mapper.UmsAdminMapper;
import com.rogear.malltiny.mbg.model.UmsAdmin;
import com.rogear.malltiny.mbg.model.UmsAdminExample;
import com.rogear.malltiny.mbg.model.UmsPermission;
import com.rogear.malltiny.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * UmsAdminServcie实现类
 * Created by Rogear on 2020/3/16
 **/
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    private Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Autowired
    private UmsAdminRoleRelationDao umsAdminRoleRelationDao;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample umsAdminExample = new UmsAdminExample();
        umsAdminExample.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> umsAdminList = umsAdminMapper.selectByExample(umsAdminExample);
        if (null != umsAdminList && umsAdminList.size()>0){
            return umsAdminList.get(0);
        }
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdmin umsAdmin) {
        UmsAdmin umsAdmin1 = new UmsAdmin();
        BeanUtil.copyProperties(umsAdmin,umsAdmin1);
        umsAdmin1.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //验证是否有相同用户名的用户
        UmsAdminExample umsAdminExample = new UmsAdminExample();
        umsAdminExample.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> umsAdminList = umsAdminMapper.selectByExample(umsAdminExample);
        if (umsAdminList.size() > 0){
            return null;
        }
        //加密密码
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin1.setPassword(encodePassword);
        umsAdminMapper.insert(umsAdmin1);
        return umsAdmin1;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码错误");
            }
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (Exception e){
            LOGGER.warn("登陆异常：{}",e.getMessage());
        }
        return token;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return umsAdminRoleRelationDao.getPermissionList(adminId);
    }
}
