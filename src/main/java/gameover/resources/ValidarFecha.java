package gameover.resources;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidarFecha implements ConstraintValidator<ValidacionFecha, String> {
	
	@Override
	public void initialize(ValidacionFecha fecha) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		final Pattern pattern = Pattern.compile("^([0-2][0-9]|(3)[0-1])(-)(((0)[0-9])|((1)[0-2]))(-)\\d{4}$");
		if (pattern.matcher(value).matches()) return true;
		else return false;
	}
}
