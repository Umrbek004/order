package org.example.order.web.valid;

import org.example.order.constants.RoleName;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckRole {
    RoleName[] value();
}
