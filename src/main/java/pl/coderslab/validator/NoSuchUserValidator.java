package pl.coderslab.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.User;

public class NoSuchUserValidator implements ConstraintValidator<NoSuchUser, String> {
	
	@Autowired
	UserDao userDao;
	
	public void initialize(NoSuchUser constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	public boolean isValid(String username, ConstraintValidatorContext context) {
		User userToFind = userDao.findUserByUsername(username);
		if(userToFind == null) {
			return false;
		}
		return true;
	}

}
