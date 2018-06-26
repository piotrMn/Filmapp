package pl.coderslab.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = NoUserNameDuplicateValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NoUserNameDuplicate {
	String message() default "Użytkownik o takiej nazwie już istnieje!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};


}
