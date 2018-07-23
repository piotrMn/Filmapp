package pl.coderslab.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.User;

public class NoUserNameDuplicateValidator implements ConstraintValidator<NoUserNameDuplicate, String> {
	
	@Autowired
	UserDao  userDao;

	public void initialize(NoUserNameDuplicate constraintAnnotation) {
		// TODO Auto-generated method stub
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		List<User> users = this.userDao.findAllUsers();
		for(User user : users) {
			if(user.getUsername().equals(value)) {
				return false;
			}
		}
		return true;
	}

}
