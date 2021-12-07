package org.dirtybiologystan.entity;

import javax.inject.Inject;

import org.dirtybiologystan.factory.CitizenFactory;
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
    	CitizenFactory citoyen = (CitizenFactory) target;
    	citoyen.setCitoyen();
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "notEmpty");

        if (citizenDetailsService.findById(citoyen.getCitoyen().getId()) != null) {
            errors.rejectValue("id", "id.exist","id already use, claim ?");
        }

        
    }


}
