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
	
	@GetMapping("/constitution")
	public String consitution() {
		return "index";
	}
	
	@GetMapping("/citoyens")
	public String citoyens() {
		return "index";
	}
	
	public String error() {
		return "error";
	}
}