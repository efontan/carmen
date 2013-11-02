package com.despegar.hackaton.carmen.config;


import com.despegar.hackaton.carmen.web.resolvers.HttpRequestContextArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import java.util.List;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ImportResource("classpath:com/despegar/hackaton/carmen/web/config/mvc-context.xml")
public class MvcConfig
    extends WebMvcConfigurerAdapter {

    @Autowired
    private ApplicationContext context;

    private int cacheSeconds = 60;
    private String defaultEncoding ="UTF-8";

    @Bean(name = "viewResolver")
    public FreeMarkerViewResolver getFreeMarkerViewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setCache(true);
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".ftl");
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setExposeSpringMacroHelpers(true);
        viewResolver.setApplicationContext(this.context);
        return viewResolver;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/css/**").addResourceLocations("/WEB-INF/css/**");
        registry.addResourceHandler("/resources/js/**").addResourceLocations("/WEB-INF/js/**");
        registry.addResourceHandler("/resources/img/**").addResourceLocations("/WEB-INF/img/**");
        registry.addResourceHandler("/resources/css-versioned/**").addResourceLocations("/WEB-INF/css-versioned/**");
        registry.addResourceHandler("/resources/js-versioned/**").addResourceLocations("/WEB-INF/js-versioned/**");
        registry.addResourceHandler("/resources/img-versioned/**").addResourceLocations("/WEB-INF/img-versioned/**");
    }

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource() {

            @Override
            protected String getDefaultMessage(String code) {
                return "?? " + code + " ??";
            }

        };

        messageSource.setBasename("/WEB-INF/messages/messages");
        messageSource.setCacheSeconds(this.cacheSeconds);
        messageSource.setDefaultEncoding(this.defaultEncoding);
        return messageSource;
    }

    @Bean(name = "freeMarkerConfigurer")
    public FreeMarkerConfigurer getFreeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setPreferFileSystemAccess(false);
        configurer.setTemplateLoaderPaths(new String[] {"/WEB-INF/views", "classpath:nibbler/freemarker"});
        Properties settings = new Properties();
        settings.setProperty("output_encoding", "UTF-8");
        configurer.setFreemarkerSettings(settings);
        return configurer;
    }


    @Bean
    public BeanNameViewResolver getBeanNameViewResolver() {
        return new BeanNameViewResolver();
    }

    @Bean(name = "jsonView")
    public MappingJacksonJsonView getMappingJacksonJsonView() {
        MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
        return jsonView;
    }

    @Bean(name = "jsonHttpMessageConverter")
    public MappingJacksonHttpMessageConverter getMappingJacksonHttpMessageConverter() {
        MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
        return converter;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new HttpRequestContextArgumentResolver());
    }
}
