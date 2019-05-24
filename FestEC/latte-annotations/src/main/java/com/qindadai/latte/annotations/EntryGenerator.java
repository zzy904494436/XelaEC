package com.qindadai.latte.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by mymac on 2019-05-24.
 * func:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)      // 源码阶段
public @interface EntryGenerator {
    String packageName();

    Class<?> entryTemplete();
}
