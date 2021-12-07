package org.dirtybiologystan;

import static org.junit.jupiter.api.Assertions.*;

import org.dirtybiologystan.entity.flag.Flag;
import org.junit.jupiter.api.Test;

class FlagTest {

	@Test
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
