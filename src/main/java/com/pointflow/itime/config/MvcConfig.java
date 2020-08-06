package com.pointflow.itime.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-07-05
 * Time: 18:13
 **/
@Configuration
public class MvcConfig  extends WebMvcConfigurerAdapter {


    @Bean //将组件注册在容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
/*                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");*/
                registry.addViewController("/main.html").setViewName("dashboard");
            }
        };
        return adapter;
    }

}
