package com.glacier.modules.sys.config;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置model 映射
 *
 * @author glacier
 * @version 1.0
 * date 2020-05-24 08:22
 */
@Configuration
public class ModelmapperConfig {

    /**
     * 将modelmapper加入容器
     *
     * @return 类型映射器
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // 智能匹配源和目标属性
        modelMapper.getConfiguration()
                .setSourceNameTokenizer(NameTokenizers.CAMEL_CASE)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setPropertyCondition(context ->
                        context.getSource() != null
                                && StringUtils.isNotBlank(context.getSource().toString()));
        return modelMapper;
    }
}
