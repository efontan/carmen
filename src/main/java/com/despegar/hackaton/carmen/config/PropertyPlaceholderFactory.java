package com.despegar.hackaton.carmen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:com/despegar/library/properties/base-properties-context.xml"})
public class PropertyPlaceholderFactory {

}
