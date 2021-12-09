package org.dirtybiologystan;

import static org.junit.jupiter.api.Assertions.*;

import org.dirtybiologystan.controler.GeneralControler;
import org.dirtybiologystan.entity.flag.Flag;
import org.junit.jupiter.api.Test;

class FlagTest {

	@Test
	void testInsertionCorrecteDesPixel() {
		try {
			GeneralControler gc = new GeneralControler();
			Integer couleur = 0xFFFFFF;
			
			for (int i = 0; i < 7000; i++) {				
				gc.affecterPixel(null,Integer.toHexString(couleur));
				couleur +=50;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//@Test
	void testChargerDataFromFouloscopie() {
		Flag f;
		f = new Flag();
		try {
			f.chargerDataFromFouloscopieAndCodati();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Erreur dans le chargement");
		}
		fail("Not yet implemented");
	}

	@Test
	void testAfficherDrapeau() {
		fail("Not yet implemented");
	}

}
