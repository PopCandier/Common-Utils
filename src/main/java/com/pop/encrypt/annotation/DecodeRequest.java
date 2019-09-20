package com.pop.encrypt.annotation;

import java.lang.annotation.*;

/**
 * 被此注解的所修饰的方法，参数将会被加密传输
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DecodeRequest {
}
