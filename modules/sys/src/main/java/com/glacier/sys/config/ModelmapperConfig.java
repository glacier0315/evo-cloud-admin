package com.glacier.sys.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置model 映射
 * @author glacier
 * @version 1.0
 * @date 2020-05-24 08:22
 */
@Configuration
public class ModelmapperConfig {

    /**
     * 将modelmapper加入容器
     * @return
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // 智能匹配源和目标属性
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        return modelMapper;
    }
}
