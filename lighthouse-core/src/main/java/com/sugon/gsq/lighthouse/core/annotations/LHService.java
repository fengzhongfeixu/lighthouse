package com.sugon.gsq.lighthouse.core.annotations;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Inherited
@Component
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LHService {

    int order() default 10000;

}
