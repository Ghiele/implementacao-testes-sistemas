package com.italoghiele.projetointegrador.validation.beanvalidation;

import com.italoghiele.projetointegrador.validation.beanvalidation.impl.CPFValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Pattern(regexp = "/^\\d{11}$/")
@Constraint(validatedBy = CPFValidator.class)
@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface CPFValid {
    String message() default "CPF must not be null and contain only alphanumeric characters.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    String value() default "";
}