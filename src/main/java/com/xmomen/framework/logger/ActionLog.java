package com.xmomen.framework.logger;

import java.lang.annotation.*;

/**
 * Created by Jeng on 16/3/20.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ActionLog {

    /** 要执行的具体操作比如：【添加商品】 **/
    String actionName();

    String remark() default "";
}
