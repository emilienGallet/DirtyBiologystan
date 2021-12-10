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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "colone", "notEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ligne", "notEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "notEmpty");
        try {			
        	if (citizenDetailsService.findById(citoyen.getCitoyen().getId()) != null) {
        		errors.rejectValue("colone", "pixel.exist","pixel already use, claim ?");
        	}
		} catch (Exception e) {
			
		}

        
    }


}
