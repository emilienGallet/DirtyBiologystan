package org.dirtybiologystan.controler;

import java.awt.Color;
import java.util.Optional;

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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

		try {
			People p = getCurentUser();
			if (p.isCitoyen()) {
				m.addAttribute("voisins", p.getVoisin(drapeau.drapeau,6));
			}else {
				m.addAttribute("voisins", null);
			}
		} catch (Exception e) {
			m.addAttribute("voisins",null);
		}

		return "realFlag/flag";
	}
	@PostMapping("/drapeau")
	@ResponseBody
	public String modifierPixel(@RequestBody String couleur) {
		try {
			System.out.println(couleur);
			People p = getCurentUser();
			if (p==null) {
				return "{\"result\":\"no\"}";
			}
			p.getPixel(this.drapeau.drapeau);
			//drapeau.rajouterNewPixel("#FF2345");
			System.out.println("ok");
			return "{\"result\":\"yes\"}";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("NON");
			return "{\"result\":\"no\"}";
		}
	}

	public String error() {
		return "error";
	}

	/**
	 * Affecte un pixel au citoyen
	 * 
	 * @throws Exception
	 */
	// @PostMapping("/pixel")
	private Pixel affecterPixel() throws Exception {
		// c.setPixel(remplacer par la ligne du dessous);
		// UserDetails userD = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// People p = peopleDetailsService.findByUsername(userD.getUsername());
		return drapeau.rajouterNewPixel("#0000");//pixel TRRANSPARENT
	}


	@GetMapping("/register")
	public String register(Model m) {
		m.addAttribute("register", new People());
		m.addAttribute("roles", PeopleRole.values());
		if (DeployInit.isLive) {
			m.addAttribute("ressourceesDeploy", DeployInit.PathResourcesDeploy);
		} else {
			m.addAttribute("ressourceesDeploy", "");
		}
		return "register";
	}

	@PostMapping("/register")
	public String addUser(@ModelAttribute("register") People p, BindingResult bindingResult) {
		peopleValidator.validate(p, bindingResult);
		if (bindingResult.hasErrors()) {
			return "/register";
		}
		if (p.defineRoleGranted()) {
			if (p.getRoles().contains(PeopleRole.NEW_CITOYEN)) {
				p.getRoles().remove(PeopleRole.NEW_CITOYEN);
				try {
					p.setPixel(this.affecterPixel());
					p.setIsSansPixel(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					// rajouter les attribut pour la page d'erreur
					return "error";// TODO
				}
				p.getRoles().add(PeopleRole.CITOYEN);
			}else if(p.getRoles().contains(PeopleRole.CITOYEN)){
				//TODO
				// Mettre un mdp pour pouvoir utiliser ce pixel
				// procédure de vérification manuel par silicyium
				return null;
			}
			peopleDetailsService.save(p);
			try {
				UserDetails userDetails = peopleDetailsService.loadUserByUsername(p.getUsername());
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, p.getPassword(), userDetails.getAuthorities());

				if (usernamePasswordAuthenticationToken.isAuthenticated()) {
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					System.err.println("OK");// log.debug(String.format("Auto login %s successfully!", email));
				}
			} catch (Exception e) {
				System.err.println("NON OK");// log.error(e.getMessage(), e);
			}
			return "redirect:/idCard/" + p.getId();
		}
		return "/register";

	}

	private People getCurentUser() throws Exception {
		UserDetails userD = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return peopleDetailsService.findByUsername(userD.getUsername());
	}

	@GetMapping("/idCard/{idCard}")
	// @ResponseBody
	public String carteIdentite(@PathVariable Long idCard, Model m) {
		try {
			People p = getCurentUser();
			if (p.getId() == idCard) {
				m.addAttribute("people", p);
				if (DeployInit.isLive) {
					m.addAttribute("ressourceesDeploy", DeployInit.PathResourcesDeploy);
				} else {
					m.addAttribute("ressourceesDeploy", "");
				}
				return "carteIdentiter";// html a crée
			} else {
				return "police";
			}
		} catch (Exception e) {
			return "police";
		}

	}

	@GetMapping("/assotiation")
	public String assosList(Model m) {
		m.addAttribute("assotiations", assotiations);
		return "register";
	}
}