package io.kangov.stixj.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE_USE;

/**
 * <p>Provides a Starts With validator of String values.</p>
 */
@Documented
@Constraint(validatedBy = { StartsWithValidator.class })
@Target( { ANNOTATION_TYPE, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface StartsWith {
    String message() default "{io.kangov.stixj.common.validation.StartsWith}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String value() default "x_";
}
