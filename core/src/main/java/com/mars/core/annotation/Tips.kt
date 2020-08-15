package com.mars.core.annotation

/**
 * @description: Tips注解，标注一些tips
 * @author: geyan
 * @date: 2020-08-15
 */
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.FIELD,
    AnnotationTarget.EXPRESSION,
    AnnotationTarget.VALUE_PARAMETER
)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class Tips constructor(val message: String = "")