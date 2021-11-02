package org.dirtybiologistan.controler;
import javax.inject.Inject;

import org.dirtybiologistan.entity.Citizen;
import org.dirtybiologistan.entity.CitizenDetailsService;
import org.dirtybiologistan.entity.CitizenValidator;
import org.dirtybiologistan.factory.CitizenFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controlleur qui supervise la gestion de l'administration de la micronation
 * @author emilien
 *
 */
@Controller
public class Administration {
	@Inject
	CitizenDetailsService citizenDetailsService;
	
	@Inject
	CitizenValidator citizenValidator;
	
	@Inject
    CitizenFactory citizenList;
	
	/**
	 * Enregistre un nouveau citoyen dans la bd
	 * @return
	 */
	public String registerCitizen() {
		//TODO Process
		return null;
	}
	
	@GetMapping("/register")
	public String registerCitizenForm(Model m) {

		m.addAttribute("register", new Citizen());
		return "register";
	}

	@PostMapping("/register")
	public String registerCitizen(@ModelAttribute("register") Citizen p, BindingResult bindingResult) {
		
		citizenValidator.validate(p, bindingResult);

		if (bindingResult.hasErrors()) {
			return "/register";
		}
		citizenDetailsService.save(p);
		return "redirect:/";
	}
	
}
