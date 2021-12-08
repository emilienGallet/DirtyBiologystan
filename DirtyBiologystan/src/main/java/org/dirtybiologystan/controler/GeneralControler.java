package org.dirtybiologystan.controler;
import org.dirtybiologystan.DeployInit;
import org.dirtybiologystan.entity.flag.Flag;
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

	private Flag drapeau = new Flag();
	
	public GeneralControler() throws Exception {
		drapeau.chargerDataFromFouloscopieAndCodati();
		// TODO Auto-generated constructor stub
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
	public String flag(Model m) {
		int xflag = drapeau.drapeau.size();// a remplacer mar méthode dans Flag.class
		int yflag = drapeau.drapeau.get(0).size();
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
}