package io.kangov.stix.validation.constraints;

import io.kangov.stix.validation.NotEmptyValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {NotEmptyValidator.class })
@Retention(RUNTIME)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Repeatable(NotEmpty.List.class)
public @interface NotEmpty {

    String message() default "{io.kangov.stix.validation.constraints.StartsWith.message})"; // (2)
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        NotEmpty[] value(); // (3)
    }

}