package org.dirtybiologystan.controler;


import java.util.List;

import javax.inject.Inject;

import org.dirtybiologystan.DeployInit;
import org.dirtybiologystan.entity.People;
import org.dirtybiologystan.entity.PeopleDetailsService;
import org.dirtybiologystan.entity.PeopleRole;
import org.dirtybiologystan.entity.PeopleValidator;
import org.dirtybiologystan.entity.flag.Flag;
import org.dirtybiologystan.entity.flag.Pixel;
import org.dirtybiologystan.repository.AssociationRepository;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version 1.0
 * @author emilien Goal : Website of the newest country : DirtyBiologistan
 * A spliter en plusieurs controlleur en V2
 */
@Controller
public class GeneralControler {

	@Inject
	PeopleDetailsService pds;

	@Inject
	PeopleValidator peopleValidator;

	@Inject
	AssociationRepository assotiations;

	private Flag drapeau = new Flag();

	/**
	 * Constructeur permetant au démarage l'import des données issue de Codati.
	 * @throws Exception
	 */
	public GeneralControler() throws Exception {
		drapeau.chargerDataFromFouloscopieAndCodati();
	}

	/**
	 * 
	 * @param m le modèle
	 * @return l'index du site web en thymeleaf
	 */
	@GetMapping("/")
	public String home(Model m) {
		if (DeployInit.isLive) {
			m.addAttribute("ressourceesDeploy", DeployInit.PathResourcesDeploy);
		} else {
			m.addAttribute("ressourceesDeploy", "");
		}
		m.addAttribute("connecter", getCurentUserOrNull());
		return "index";
	}

	/**
	 * Retourne la constitution de la micronation
	 * 
	 * @return constitution.html
	 */
	@GetMapping("/constitution")
	public String consitution(Model m) {
		if (DeployInit.isLive) {
			m.addAttribute("ressourceesDeploy", DeployInit.PathResourcesDeploy);
		} else {
			m.addAttribute("ressourceesDeploy", "");
		}
		m.addAttribute("isConnected", getCurentUserOrNull());
		return "constitution";
	}

	/**
	 * 
	 * @return citoyen.html
	 */
	@GetMapping("/citoyens")
	public String citoyens(Model m) {
		if (DeployInit.isLive) {
			m.addAttribute("ressourceesDeploy", DeployInit.PathResourcesDeploy);
		} else {
			m.addAttribute("ressourceesDeploy", "");
		}
		m.addAttribute("isConnected", getCurentUserOrNull());
		return "citoyens";
	}

	/**
	 * Le mettre en rest
	 * 
	 * @return le tag de la personne
	 */
	@GetMapping("/citoyens/{colone}/{ligne}")
	public String citoyensID(Model m) {
		if (DeployInit.isLive) {
			m.addAttribute("ressourceesDeploy", DeployInit.PathResourcesDeploy);
		} else {
			m.addAttribute("ressourceesDeploy", "");
		}
		m.addAttribute("isConnected", getCurentUserOrNull());
		return "citoyens";
	}

	/**
	 * 
	 * @param m
	 * @return drapeau.html avec les param passer a thymeleaf.
	 * @throws Exception
	 */
	@GetMapping("/drapeau")
	public String flag(Model m) throws Exception {
		/**
		 * La localisation des resources est différente lors du déploiment.
		 */
		if (DeployInit.isLive) {
			m.addAttribute("pixies", drapeau.drapeau);
			m.addAttribute("ressourceesDeploy", DeployInit.PathResourcesDeploy);
		} else {
			m.addAttribute("pixies", drapeau.drapeau);
			m.addAttribute("ressourceesDeploy", "");
		}
		//Si l'utilisateur est connecter, alors il peux afficher ces pixels voisins et parametrer son pixel.
		try {
			People p = getCurentUser();
			if (p.isCitoyen()) {
				m.addAttribute("voisins", p.getVoisin(drapeau.drapeau,6));
				m.addAttribute("isConnected", p);
			}else {
				m.addAttribute("voisins", null);
				m.addAttribute("isConnected", null);
			}
		} catch (Exception e) {
		//Sinon on n'affiche rien
			m.addAttribute("voisins",null);
			m.addAttribute("isConnected", null);
		}

		return "realFlag/flag";
	}
	/**
	 * 
	 * @param couleur
	 * @return en JSON la réponse oui ou non si le pixel a été bien modifier
	 */
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

	/**
	 * 
	 * @return error.html
	 */
	public String error(Model m) {
		m.addAttribute("isConnected", getCurentUserOrNull());
		return "error";
	}

	/**
	 * Affecte un pixel au citoyen
	 * 
	 * @throws Exception
	 */
	private Pixel affecterPixel() throws Exception {
		return drapeau.rajouterNewPixel("#0000");//pixel TRRANSPARENT
	}


	/**
	 * 
	 * @param m
	 * @return register.html
	 */
	@GetMapping("/register")
	public String register(Model m) {
		if (getCurentUserOrNull()!=null) {
			return "redirect:/";
		}
		m.addAttribute("register", new People());
		m.addAttribute("roles", PeopleRole.values());
		if (DeployInit.isLive) {
			m.addAttribute("ressourceesDeploy", DeployInit.PathResourcesDeploy);
		} else {
			m.addAttribute("ressourceesDeploy", "");
		}
		m.addAttribute("isConnected", null);
		return "register";
	}

	/**
	 * 
	 * @param p
	 * @param bindingResult
	 * @return la route pour afficher la carte d'identité 
	 * sinon renvoie sur le formulaire ou la route de la police (non définie)
	 */
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
					// TODO rajouter les attribut pour la page d'erreur 
					e.printStackTrace();
					return "error";
				}
				p.getRoles().add(PeopleRole.CITOYEN);
			}else if(p.getRoles().contains(PeopleRole.CITOYEN)){
				// TODO
				// Mettre un mdp pour pouvoir utiliser ce pixel
				// procédure de vérification manuel par silicyium
				// En attendant ... 
				if(!pds.checkID(p,drapeau)) {
					return "redirect:/police";//renvoyer sur une convocation au comissariat x)					
				}
			}
			// Tout est ok on va enregistrer la personne et l'auto connecter.
			pds.save(p);
			try {
				UserDetails userDetails = pds.loadUserByUsername(p.getUsername());
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, p.getPassword(), userDetails.getAuthorities());

				if (usernamePasswordAuthenticationToken.isAuthenticated()) {
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					System.err.println("OK");// log.debug(String.format("Auto login %s successfully!", email));
				}
			} catch (Exception e) {
				//Partie non atteinte normalement
				System.err.println("NON OK");// log.error(e.getMessage(), e);
			}
			return "redirect:/idCard/" + p.getId();
		}
		return "/register";

	}
	
	/**
	 * 
	 * @return la personne conecter ou renvoie la valeu null
	 */
	private People getCurentUserOrNull() {
		try {
			return getCurentUser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	private People getCurentUser() throws Exception {
		UserDetails userD = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return pds.findByUsername(userD.getUsername());
	}

	/**
	 * 
	 * @param idCard
	 * @param m
	 * @return carteIdentiter.html
	 * sinon la route de la police (non définie)
	 */
	@GetMapping("/idCard/{idCard}")
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
				m.addAttribute("isConnected", p);
				return "carteIdentiter";// html a crée
			} else {
				return "redirect:/police";
			}
		} catch (Exception e) {
			return "redirect:/police";
		}

	}

	@GetMapping("/assotiation")
	public String assosList(Model m) {
		m.addAttribute("assotiations", assotiations);
		m.addAttribute("isConnected", getCurentUserOrNull());
		return "register";
	}
	
	/**
	 * Renvoie toute les données de la base de donnée afin de rendre acte des données personnel que l'on récolte.
	 * @return
	 */
    @RequestMapping("/allDB")
    @ResponseBody
    public List<People> allDB(){
    	return pds.getAllUsers();
    }	
}