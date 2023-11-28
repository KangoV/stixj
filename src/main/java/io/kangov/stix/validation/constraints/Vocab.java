package io.kangov.stix.validation.constraints;

import io.kangov.stix.enums.Vocabs;
import io.kangov.stix.validation.VocabValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(Vocab.List.class)
@Documented
@Constraint(validatedBy = { VocabValidator.class })
public @interface Vocab {

    Vocabs.Vocab value();

    String message() default "{io.kangov.stix.validation.constraints.Vocab.message})"; // (2)
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Vocab[] value(); // (3)
    }

}