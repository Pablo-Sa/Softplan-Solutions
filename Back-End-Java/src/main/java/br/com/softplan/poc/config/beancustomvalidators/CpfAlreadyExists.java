package br.com.softplan.poc.config.beancustomvalidators;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CpfNotExistsValidator.class)
public @interface CpfAlreadyExists {
	
    String message() default "Valor inválido";
    
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
 
    String value() default "";

}
