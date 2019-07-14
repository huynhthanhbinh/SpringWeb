package com.bht.validators;

import com.bht.models.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


// Validator for model User
@Component
public class UserValidator implements Validator {

    // Support method
    // make sure that Validator use for supported class
    // Eg. class UserValidator support class User
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }


    // Customize Validator for User (model) object
    // Must down-casting from (Object) to (User) o
    // Errors argument is used for binding in Controller
    // ... if (bindingResult.hasErrors()) do_sth ...
    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            // username is the field having error ! Error throw at username
            // Throw an error as a map < error_key , error_value>
            // <error_key>   : Eg. field.required
            // <error_value> : Eg. This field is compulsory
            // <error_value> message is configure in messages.properties
            errors.rejectValue("username", "field.required");
        }


        // Util -> Utility
        // This util is provided by the Spring Framework : ValidationUtils
        // supported methods for validate an empty object model : Eg. rejectIfEmpty()
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");


        // Validate the length of password min: 6, max 20 characters
        if (user.getPassword().length() < 6 || user.getPassword().length() > 20) {
            errors.rejectValue("password", "password.length");
        }


        // Check if password cannot contains whitespace !
        if (user.getPassword().contains(" ")) {
            errors.rejectValue("password", "password.whitespace");
        }


        // Check if ID is a positive number
        if (user.getId() < 1) {
            errors.rejectValue("id", "id.negative");
        }


        // Check if email is in correct form ! using regex
        // Email format for validate
        String emailFormat = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
        if (!user.getEmail().matches(emailFormat)) {
            errors.rejectValue("email", "email.incorrect");
        }

        // Check if user has accepted the Agreement
        if (!user.isAcceptAgreement()) {
            errors.rejectValue("acceptAgreement", "agreement.accept");
        }
    }
}
