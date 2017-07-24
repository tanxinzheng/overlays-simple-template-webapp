package com.xmomen.framework.web.bind.annotation;

import java.lang.annotation.*;

/**
 * Created by tanxinzheng on 17/6/16.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
}
