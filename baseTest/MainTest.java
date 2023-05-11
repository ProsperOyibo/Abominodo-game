package baseTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import base.HighScore;
import base.IOSpecialist;
import base.Main;
import base.RulesFrame;

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
	
	@Test
	void testMenuOption2() {
		Main mf = new Main();
		IOSpecialist io = new IOSpecialist();
		mf.mainMenuOption();
		assertEquals(2, mf.getOption(io));
		new HighScore("prosper");
	}
	
	@Test
	void testMenuOption3() {
		Main mf = new Main();
		IOSpecialist io = new IOSpecialist();
		mf.mainMenuOption();
		assertEquals(3, mf.getOption(io));
		new RulesFrame();
	}
	
	@Test
	void testMenuOption5() {
		Main mf = new Main();
		IOSpecialist io = new IOSpecialist();
		mf.mainMenuOption();
		assertEquals(5, mf.getOption(io));
		mf.getInspiration();
	}
	
	
	

}
