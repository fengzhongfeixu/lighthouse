package com.sugon.gsq.lighthouse.agent.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;

/*
 * ClassName: TomcatConfig
 * Author: gsq
 * Date: 2020/4/1 18:13
 */
@Configuration
public class TomcatConfig implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Value("${server.agent.port}")
    private Integer agent_port;

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setPort(agent_port);
    }
}
