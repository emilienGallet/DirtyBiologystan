package org.dirtybiologystan.controler;

import java.awt.Color;

import javax.inject.Inject;

import org.dirtybiologystan.DeployInit;
import org.dirtybiologystan.entity.Citizen;
import org.dirtybiologystan.entity.People;
import org.dirtybiologystan.entity.PeopleDetailsService;
import org.dirtybiologystan.entity.PeopleRole;
import org.dirtybiologystan.entity.PeopleValidator;
import org.dirtybiologystan.entity.flag.Flag;
import org.dirtybiologystan.entity.flag.Pixel;
import org.dirtybiologystan.factory.CitizenFactory;
import org.dirtybiologystan.factory.PeopleFactory;
import org.dirtybiologystan.repository.AssociationRepository;
import org.dirtybiologystan.repository.CitizenRepository;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @version 1.0
 * @author emilien Goal : Website of the newest country : DirtyBiologistan
 *
 */
@Controller
public class GeneralControler {

	@Inject
	PeopleDetailsService peopleDetailsService;

	@Inject
	PeopleValidator peopleValidator;

	@Inject
	CitizenRepository citizenList;

	@Inject
	AssociationRepository assotiations;

	private Flag drapeau = new Flag();

	public GeneralControler() throws Exception {
		drapeau.chargerDataFromFouloscopieAndCodati();
	}

	@GetMapping("/")
	public String home(Model m) {
		if (DeployInit.isLive) {
			m.addAttribute("ressourceesDeploy", DeployInit.PathResourcesDeploy);
		} else {
			m.addAttribute("ressourceesDeploy", "");
		}
		return "index";
	}

	/**
	 * Retourne la constitution de la micronation
	 * 
	 * @return
	 */
	@GetMapping("/constitution")
	public String consitution() {
		return "constitution";
	}

	@GetMapping("/citoyens")
	public String citoyens() {
		return "citoyens";
	}

	/**
	 * Le mettre en rest
	 * 
	 * @return le tag de la personne
	 */
	@GetMapping("/citoyens/{colone}/{ligne}")
	public String citoyensID() {
		return "citoyens";
	}

	@GetMapping("/drapeau")
	public String flag(Model m) throws Exception {
		// Integer couleur = 0x000000;
		/*
		 * for (int i = 0; i < 100000/*1056
		 *//*
			 * ; i++) { this.affecterPixel(null,"#FF2345"); //couleur +=50; }
			 */

		int xflag = drapeau.drapeau.size();// a remplacer mar méthode dans Flag.class
		int yflag = drapeau.drapeau.get(1).size();
		System.err.println(yflag + "*" + xflag);
		if (DeployInit.isLive) {
			m.addAttribute("pixies", drapeau.drapeau);
			m.addAttribute("ressourceesDeploy", DeployInit.PathResourcesDeploy);
		} else {
			m.addAttribute("pixies", drapeau.drapeau);
			m.addAttribute("ressourceesDeploy", "");
		}
		return "realFlag/flag";
	}

	public String error() {
		return "error";
	}

	/**
	 * Affecte un pixel au citoyen
	 * 
	 * @throws Exception
	 */
	//@PostMapping("/pixel")
	private Pixel affecterPixel() throws Exception{
		// c.setPixel(remplacer par la ligne du dessous);
		UserDetails userD = (UserDetails)
		SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		People p = peopleDetailsService.findByUsername(userD.getUsername());
		return drapeau.rajouterNewPixel("#FF2345");
	}
	@GetMapping("/pixel")
	public String modifierPixel() {
		try {
			drapeau.rajouterNewPixel("#FF2345");
			return "redirect:/drapeau";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/";
		}
	}

	/**
	 * Enregistre un nouveau citoyen dans la bd
	 * 
	 * @return
	 */
	public String registerCitizen() {
		// TODO Process
		return null;
	}

	@GetMapping("/register")
	public String register(Model m) {
		m.addAttribute("register", new People());
		m.addAttribute("roles", PeopleRole.values());
		return "register";
	}

	@PostMapping("/register")
	public String addUser(@ModelAttribute("register") People p, BindingResult bindingResult) {
		peopleValidator.validate(p, bindingResult);
		if (bindingResult.hasErrors()) {
			return "/register";
		}
		p.defineRoleGranted();
		peopleDetailsService.save(p);
		
		
		if (p.getRoles().contains(PeopleRole.NEW_CITOYEN)) {
			p.getRoles().remove(PeopleRole.NEW_CITOYEN);
			try {
				p.setPixel(this.affecterPixel());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error";//TODO
			}
			p.getRoles().add(PeopleRole.CITOYEN);
		}
		peopleDetailsService.save(p);
		return "redirect:/";
	}

	@GetMapping("/assotiation")
	public String assosList(Model m) {
		m.addAttribute("assotiations", assotiations);
		return "register";
	}
}