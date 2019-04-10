package com.zhou.datasource.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IDEA
 *
 * @author : zhoupan
 * @date : 2019/4/9 10:00
 * @info :
 */

/**
 * 注解生命周期作用范围
 */
@Retention(RetentionPolicy.RUNTIME)
/**
 *注解作用在参数上
 */
@Target({
        ElementType.METHOD,ElementType.PARAMETER
})
public @interface DBChange {


}
