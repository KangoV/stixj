package io.kangov.stix.validation.constraints;

import io.kangov.stix.validation.RangeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(Range.List.class)
@Documented
@Constraint(validatedBy = { RangeValidator.class })
public @interface Range {

	int min() default Integer.MIN_VALUE;
	int max() default Integer.MAX_VALUE;

	String message() default "invalid range ({validatedValue})";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };

	/**
	 * Defines several {@code @Length} annotations on the same element.
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		Range[] value();
	}
}
