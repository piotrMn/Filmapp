package pl.coderslab.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pl.coderslab.util.Converter;

public class PastDateValidator implements ConstraintValidator<PastDate, String> {
	
	public void initialize(PastDate constraintAnnotation) {
		// TODO Auto-generated method stub
	}

	public boolean isValid(String dateString, ConstraintValidatorContext context) {
		if(dateString.equals("") || dateString == null) {
			return true;
		}
		String dateStringTrimmed = dateString.trim();
//		String dateReversed = Converter.reverseDate(dateStringTrimmed);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
		Date thisDate = null;
		try {
			thisDate = sdf.parse(dateStringTrimmed.concat(" 23:59:59"));
		} catch (ParseException e) {
			return false;
		}
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		if(thisDate.before(now)) {
			return false;
		}
		return true;
	}

}
