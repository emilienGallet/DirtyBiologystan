package org.dirtybiologystan.controler;
import javax.inject.Inject;

import org.dirtybiologystan.entity.Citizen;
import org.dirtybiologystan.entity.CitizenDetailsService;
import org.dirtybiologystan.entity.CitizenValidator;
import org.dirtybiologystan.factory.AssociationFactory;
import org.dirtybiologystan.factory.CitizenFactory;
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
public class AdministrationControler {
	@Inject
	CitizenDetailsService citizenDetailsService;
	
	@Inject
	CitizenValidator citizenValidator;
	
	@Inject
    CitizenFactory citizenList;
	
	@Inject
	AssociationFactory assotiations;
	
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
	
	@GetMapping("/assotiation")
	public String assosList(Model m) {
		m.addAttribute("assotiations", assotiations);
		return "register";
	}
	
}
