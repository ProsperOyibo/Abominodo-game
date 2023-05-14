package tests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import base.HighScore;
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
	void testMenuOption() {
		Main mf = new Main();
		mf.menu.mainMenuOption();
		assertEquals(1, mf.getOption(1));
		mf.menu.selectDifficulty();
		assertEquals(2, mf.getOption(2));
		new HighScore("prosper");
		assertEquals(3, mf.getOption(3));
		new RulesFrame();
		assertEquals(5, mf.getOption(5));
		mf.menu.getInspiration();
	}

	
	@Test
	void testPlayMenuOption() {
		Main mf = new Main();
		mf.menu.playMenu("prosper");
		assertEquals(1, mf.getPlayMenuOption(1));
		assertEquals(2, mf.getPlayMenuOption(2));
		assertEquals(3, mf.getPlayMenuOption(3));
		assertEquals(4, mf.getPlayMenuOption(4));
		assertEquals(5, mf.getPlayMenuOption(5));
		assertEquals(6, mf.getPlayMenuOption(6));
		assertEquals(7, mf.getPlayMenuOption(7));
		mf.printScore();
		assertEquals(0, mf.getPlayMenuOption(0));
	}
	
	@Test
	void testgetDifficultyOption() {
		Main mf = new Main();
		mf.menu.selectDifficulty();
		assertEquals(1, mf.getDifficulty(1));
		assertEquals(2, mf.getDifficulty(2));
		assertEquals(3, mf.getDifficulty(3));
	}
	
	
	
	

}
