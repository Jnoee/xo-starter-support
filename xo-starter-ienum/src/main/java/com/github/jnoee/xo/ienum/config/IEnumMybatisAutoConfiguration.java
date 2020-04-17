package com.github.jnoee.xo.ienum.config;

import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.jnoee.xo.ienum.IEnumManager;
import com.github.jnoee.xo.ienum.mybatis.IEnumTypeHandler;

@Configuration
@ConditionalOnClass(TypeHandler.class)
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class IEnumMybatisAutoConfiguration {
  @Bean
  ConfigurationCustomizer ienumMybatisConfigurationCustomizer() {
    return configuration -> IEnumManager.getIenums().keySet().forEach(ienumClass -> configuration
        .getTypeHandlerRegistry().register(ienumClass, IEnumTypeHandler.class));
  }
}
