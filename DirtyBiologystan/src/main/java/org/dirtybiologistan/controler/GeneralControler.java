package org.dirtybiologistan.controler;
import org.springframework.stereotype.Controller;
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
	public String home() {
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
	
	public String error() {
		return "error";
	}
}