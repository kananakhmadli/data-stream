package com.company.config;

import az.kapitalbank.atlas.lib.common.config.annotation.EnableContextFilter;
import az.kapitalbank.atlas.lib.common.config.annotation.EnableErrorHandler;
import az.kapitalbank.atlas.lib.common.config.annotation.EnableLogInterceptor;
import az.kapitalbank.atlas.lib.common.config.annotation.EnableSwagger;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSwagger
@EnableErrorHandler
@EnableContextFilter
@EnableLogInterceptor
public class LibConfiguration {
}