package tests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.io.File;


import base.HighScore;

class HighScoreTest {

	@Test
	void testHighScoreContructor1() {
		HighScore sc = new HighScore("Silver");
		assertEquals("Silver", sc.playerName);
	}
	
	@Test
	void testHighScoreContructor2() {
		HighScore sc = new HighScore("Thomas");
		assertEquals("Thomas", sc.playerName);
	}
	
	@Test
	void testSaveHighScore1() {
		HighScore sc = new HighScore("Silver");
		File testScore = new File("testscore.txt");
		assertEquals(testScore, sc.saveHighScore(testScore));
	}
	
	@Test
	void testSaveHighScore2() {
		HighScore sc = new HighScore("Mark");
		File testScore = new File("testscore.txt");
		assertEquals(testScore, sc.saveHighScore(testScore));
	}

}
