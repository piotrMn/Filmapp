package pl.coderslab.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotDateValidator implements ConstraintValidator<NotDate, String> {
	
	public void initialize(NotDate constraintAnnotation) {
		// TODO Auto-generated method stub
	}

	public boolean isValid(String dateString, ConstraintValidatorContext context) {
		if(dateString.equals("") || dateString == null) {
			return true;
		}
		String dateStringTrimmed = dateString.trim();
		String regex = "^\\d{1,2}-\\d{1,2}-\\d{4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(dateStringTrimmed);
		if(matcher.matches()) {
			String[] dateSplit = dateString.split("-");
			int day = Integer.parseInt(dateSplit[0]);
			if(day > 31 || day < 1) {
				return false;
			}
			int month = Integer.parseInt(dateSplit[1]);
			if(month > 12 || month < 1) {
				return false;
			}
			int year = Integer.parseInt(dateSplit[2]);
			if(year < 2000 || year > 2100) {
				return false;
			}
			return true;
		}
		return false;
	}
}
