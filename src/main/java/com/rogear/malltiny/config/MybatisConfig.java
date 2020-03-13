package com.rogear.malltiny.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Rogear on 2020/3/13
 **/
@Configuration
@MapperScan({"com.rogear.malltiny.mbg.mapper","com.rogear.malltiny.dao"})
public class MybatisConfig {
}
