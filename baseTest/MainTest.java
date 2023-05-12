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
		mf.menu.mainMenuOption();
		assertEquals(1, mf.getOption(1));
		mf.menu.selectDifficulty();
	}
	
	@Test
	void testMenuOption2() {
		Main mf = new Main();
		mf.menu.mainMenuOption();
		assertEquals(2, mf.getOption(2));
		new HighScore("prosper");
	}
	
	@Test
	void testMenuOption3() {
		Main mf = new Main();
		mf.menu.mainMenuOption();;
		assertEquals(3, mf.getOption(3));
		new RulesFrame();
	}
	
	@Test
	void testMenuOption5() {
		Main mf = new Main();
		mf.menu.mainMenuOption();
		assertEquals(5, mf.getOption(5));
		mf.menu.getInspiration();
	}
	
	@Test
	void testgetDifficultyOption1() {
		Main mf = new Main();
		mf.menu.selectDifficulty();
		assertEquals(1, mf.getDifficulty(1));
	}
	
	@Test
	void testgetDifficultyOption2() {
		Main mf = new Main();
		mf.menu.selectDifficulty();
		
		assertEquals(2, mf.getDifficulty(2));
	}
	
	@Test
	void testgetDifficultyOption3() {
		Main mf = new Main();
		mf.menu.selectDifficulty();
		assertEquals(3, mf.getDifficulty(3));
	}
	
	
	

}
