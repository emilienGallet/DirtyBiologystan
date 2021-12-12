package org.dirtybiologystan.entity;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Jérémy Goutelle
 */

@Component
public class PeopleValidator implements Validator {

    @Inject
    private PeopleDetailsService peopleDetailsService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        People people = (People) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "notEmpty");

        if (peopleDetailsService.findByUsername(people.getUsername()) != null) {
            errors.rejectValue("username", "user.exist","Username already use");
        }
        
        
    }

}
