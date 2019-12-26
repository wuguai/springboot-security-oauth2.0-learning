package com.sweetmart.core.mybatisplus.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.sweetmart.modules.*.mapper*"})
public class MybatisPlusConfig {
    public MybatisPlusConfig() {
    }
}
