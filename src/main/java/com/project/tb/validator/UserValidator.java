package com.project.tb.validator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.project.tb.models.User;
@Component
public class UserValidator implements Validator{
	
/*
 * to be deleted
 */
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		User user = (User)object;
		if (user.getPassword().length()<6) {
			errors.rejectValue("password", "Length","Password must be at least six characters");
		}
	}

}
