package cn.cashbang.core.common.annotation;

import java.lang.annotation.*;

/**
 * 数据源注解
 * @author Tiny
 * @date 2018/2/7
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    String value() default "";

}
