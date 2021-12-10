package org.dirtybiologystan.controler;
import java.awt.Color;

import javax.inject.Inject;

import org.dirtybiologystan.DeployInit;
import org.dirtybiologystan.entity.Citizen;
import org.dirtybiologystan.entity.CitizenDetailsService;
import org.dirtybiologystan.entity.flag.Flag;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @version 1.0
 * @author emilien
 * Goal : Website of the newest country : DirtyBiologistan
 *
 */
@Controller
public class GeneralControler {

	@Inject
	CitizenDetailsService citizenDetailsService;
	
	private Flag drapeau = new Flag();
	
	public GeneralControler() throws Exception {
		drapeau.chargerDataFromFouloscopieAndCodati();
	}
	
	@GetMapping("/")
	public String home(Model m) {
		if (DeployInit.isLive) {			
			m.addAttribute("ressourceesDeploy",DeployInit.PathResourcesDeploy);
		}else {
			m.addAttribute("ressourceesDeploy","");
		}
		return "index";
	}
	
	/**
	 * Retourne la constitution de la micronation
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
	 * @return le tag de la personne 
	 */
	@GetMapping("/citoyens/{colone}/{ligne}")
	public String citoyensID() {
		return "citoyens";
	}
	
	@GetMapping("/drapeau")
	public String flag(Model m) throws Exception {
		//Integer couleur = 0x000000;
		/*
		for (int i = 0; i < 100000/*1056*//*; i++) {				
			this.affecterPixel(null,"#FF2345");
			//couleur +=50;
		}*/
		
		int xflag = drapeau.drapeau.size();// a remplacer mar méthode dans Flag.class
		int yflag = drapeau.drapeau.get(1).size();
		System.err.println(yflag+"*"+xflag);
		if (DeployInit.isLive) {			
			m.addAttribute("pixies",drapeau.drapeau);
			m.addAttribute("ressourceesDeploy",DeployInit.PathResourcesDeploy);
		}else {
			m.addAttribute("pixies",drapeau.drapeau);
			m.addAttribute("ressourceesDeploy","");
		}
		return "realFlag/flag";
	}
	public String error() {
		return "error";
	}
	
	/**
	 * Affecte un pixel au citoyen
	 * @throws Exception 
	 */
	@PostMapping("/pixel")
	public void affecterPixel(Model m) throws Exception {
		//c.setPixel(remplacer par la ligne du dessous);
		UserDetails userD = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Citizen c = citizenDetailsService.findById(userD.getUsername());
		drapeau.rajouterNewPixel("#FF2345");
	}
}