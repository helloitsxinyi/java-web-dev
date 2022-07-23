package sg.edu.iss.caps.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.edu.iss.caps.model.Account;

@Component
public class AccountValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Account.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Account a = (Account) target;
		ValidationUtils.rejectIfEmpty(errors, "username", "error.account.login.username","Username must not be empty");
		ValidationUtils.rejectIfEmpty(errors, "password","error.account.login.password", "Password must not be empty");
	    System.out.println(a.toString());
	}

}
