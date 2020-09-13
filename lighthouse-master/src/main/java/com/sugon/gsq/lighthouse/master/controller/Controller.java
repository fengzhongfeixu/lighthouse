package com.sugon.gsq.lighthouse.master.controller;

import com.sugon.gsq.lighthouse.core.annotations.LHService;
import com.sugon.gsq.lighthouse.core.app.hdfs.Namenode;
import com.sugon.gsq.lighthouse.core.util.SpringBeanFinder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/*
 * ClassName: Controller
 * Author: Administrator
 * Date: 2020/8/25 10:28
 */
@RestController
@RequestMapping("/test")
public class Controller implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    }

    @PostMapping("/test")
    public void test(){
        SpringBeanFinder.getBeanNamesByAnno(LHService.class).forEach(k -> {
            List<Annotation> annotations = Arrays.asList(k.getClass().getAnnotations());
            annotations.forEach(annotation -> {
                System.out.println(annotation instanceof LHService);
            });
        });
    }

}
