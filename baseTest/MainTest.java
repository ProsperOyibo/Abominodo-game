package baseTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import base.IOSpecialist;
import base.Main;

class MainTest {
	
	@Test
	void testDominoCount() {
		assertEquals(28, Main.DOMINO_COUNT);
	}
	
	@Test
	void testPrintGrid() {
		Main mf = new Main();
		assertEquals(11, mf.printGrid());
		assertEquals(11, mf.printGuessGrid());
	}
	

}
