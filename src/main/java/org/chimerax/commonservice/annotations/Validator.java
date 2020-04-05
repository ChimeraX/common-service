package org.chimerax.commonservice.annotations;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Author: Silviu-Mihnea
 * Date: 05-Apr-20
 * Time: 10:12 AM
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Validator {

    @AliasFor(annotation = Component.class)
    String value() default "";

}