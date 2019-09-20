package com.pop.encrypt.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EncodeRequest {
    /**
     * 由服务端提供的密匙
     * @return
     */
    String key() ;
}
