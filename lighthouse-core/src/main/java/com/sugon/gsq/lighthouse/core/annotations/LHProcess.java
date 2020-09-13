package com.sugon.gsq.lighthouse.core.annotations;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Inherited
@Component
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LHProcess {

    int order() default 10000;

}
