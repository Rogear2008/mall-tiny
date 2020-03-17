package com.rogear.malltiny.config;

import com.rogear.malltiny.component.JwtAuthenticationTokenFilter;
import com.rogear.malltiny.component.RestAuthenticationEntryPoint;
import com.rogear.malltiny.component.RestfulAccessDeniedHandler;
import com.rogear.malltiny.dto.AdminUserDetails;
import com.rogear.malltiny.mbg.model.UmsAdmin;
import com.rogear.malltiny.mbg.model.UmsPermission;
import com.rogear.malltiny.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * spring security的配置
 * Created by Rogear on 2020/3/16
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UmsAdminService umsAdminService;

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf()  //我们使用的是JWT，不需要crsf
                .disable()
                .sessionManagement()  //基于token，不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,  //允许对网站的静态资源访问
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**",
                        "/webjars/springfox-swagger-ui/**"
                        )
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS)  //跨域请求会先进行一次options请求
                .permitAll()
                .antMatchers("/admin/login","/admin/register")
                .permitAll()
                .antMatchers("/esProduct/**","/member/readHistory/**")  //测试时开放
                .permitAll()
                .anyRequest() //除上面的所有请求都需要鉴权认证
                .authenticated();
        //禁用缓存
        httpSecurity.headers().cacheControl();
        //添加JWT的filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未登录和未授权结果返回
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        //获取登陆用户信息
        return username ->{
            UmsAdmin umsAdmin = umsAdminService.getAdminByUsername(username);
            if (umsAdmin != null){
                List<UmsPermission> permissionList = umsAdminService.getPermissionList(umsAdmin.getId());
                return new AdminUserDetails(umsAdmin,permissionList);
            }
            throw new UsernameNotFoundException("用户名或者密码错误");
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

}
