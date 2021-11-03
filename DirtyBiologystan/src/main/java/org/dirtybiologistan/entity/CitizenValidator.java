package org.dirtybiologistan.entity;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Émilien Gallet
 */

@Component
public class CitizenValidator implements Validator {

    @Inject
    private CitizenDetailsService citizenDetailsService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Citizen citoyen = (Citizen) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "notEmpty");

        if (citizenDetailsService.findById(citoyen.getId()) != null) {
            errors.rejectValue("id", "id.exist","id already use, claim ?");
        }

        
    }


}
