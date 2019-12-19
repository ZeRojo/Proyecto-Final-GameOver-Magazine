package gameover.resources;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ValidarFecha.class)
public @interface ValidacionFecha {
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.FIELD, ElementType.METHOD })
	public @interface ValidacionDNI {
		String message() default "No és un formato de fecha válido (dd-MM-yyyy)";
		
		Class<?>[] groups() default {};
		
		Class<? extends Payload>[] payload() default {};
	}


}
