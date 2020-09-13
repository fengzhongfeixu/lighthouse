package com.sugon.gsq.lighthouse.master.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;

/*
 * ClassName: TomcatConfig
 * Author: gsq
 * Date: 2019/9/17 18:13
 */
@Configuration
public class TomcatConfig implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Value("${server.master.port}")
    private Integer master_port;

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setPort(master_port);
    }
}
