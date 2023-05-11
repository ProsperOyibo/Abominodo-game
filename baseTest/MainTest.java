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
	
	@Test
	void testWelcomeMessageAndPlayerName() {
		Main mf = new Main();
		mf.welcomeMessage();
		assertEquals("prosper",mf.getPlayerName());
	}
	
	@Test
	void testMenuOption1() {
		Main mf = new Main();
		IOSpecialist io = new IOSpecialist();
		mf.mainMenuOption();
		assertEquals(1, mf.getOption(io));
		mf.selectDifficulty();
	}
	
	
	

}
