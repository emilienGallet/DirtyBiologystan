package org.dirtybiologystan.controler;
import org.dirtybiologystan.DeployInit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @version 1.0
 * @author emilien
 * Goal : Website of the newest country : DirtyBiologistan
 *
 */
@Controller
public class GeneralControler {

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
	public String constitution() {
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
	
	public String error() {
		return "error";
	}
}