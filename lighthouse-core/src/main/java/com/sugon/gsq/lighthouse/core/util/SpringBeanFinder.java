package com.sugon.gsq.lighthouse.core.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sugon.gsq.lighthouse.core.annotations.LHConfig;
import com.sugon.gsq.lighthouse.core.annotations.LHProcess;
import com.sugon.gsq.lighthouse.core.annotations.LHService;
import com.sugon.gsq.lighthouse.core.app.IBase;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public final class SpringBeanFinder implements ApplicationContextAware {

    private static ApplicationContext context;

    public static <T> T getBean(Class<T> c) {
        return context.getBean(c);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return context.getBean(name, clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return context;
    }

    public static List<? extends IBase> getBeanNamesByAnno(Class<? extends Annotation> clazz) {
        Class<? extends Annotation> annotationClass = clazz;
        Map<String, Object> beanWhithAnnotation = context.getBeansWithAnnotation(annotationClass);
        Set<Map.Entry<String, Object>> entitySet = beanWhithAnnotation.entrySet();
        LinkedList result = Lists.newLinkedList();
        for (Map.Entry<String, Object> entry : entitySet) {
            Class<? extends Object> clacc = entry.getValue().getClass();
//            Annotation componentDesc = AnnotationUtils.findAnnotation(clacc, clazz);
            result.add(getBean(clacc));
        }
        return result;
    }
}
