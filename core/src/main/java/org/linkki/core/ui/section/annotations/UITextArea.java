package org.linkki.core.ui.section.annotations;

import static org.linkki.core.ui.section.annotations.EnabledType.ENABLED;
import static org.linkki.core.ui.section.annotations.RequiredType.NOT_REQUIRED;
import static org.linkki.core.ui.section.annotations.VisibleType.VISIBLE;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UITextArea {

    int position();

    String label() default "";

    boolean noLabel() default false;

    EnabledType enabled() default ENABLED;

    RequiredType required() default NOT_REQUIRED;

    VisibleType visible() default VISIBLE;

    int rows() default 1;

    int columns() default 0;

    int maxLength() default 0;

    Class<?> modelClass() default NoModelClassProvided.class;

    String modelAttribute() default "";

}