package pl.coderslab.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PastDateValidator implements ConstraintValidator<PastDate, String> {
	
	public void initialize(PastDate constraintAnnotation) {
	}

	public boolean isValid(String dateString, ConstraintValidatorContext context) {
		if(dateString.equals("") || dateString == null) {
			return true;
		}
		String dateStringTrimmed = dateString.trim();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
		Date thisDate = null;
		try {
			thisDate = sdf.parse(dateStringTrimmed.concat(" 23:59:59"));
		} catch (ParseException e) {
			return false;
		}
		Date now = Calendar.getInstance().getTime();
		if(thisDate.before(now)) {
			return false;
		}
		return true;
	}

}
