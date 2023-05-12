package baseTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import base.HighScore;

class HighScoreTest {

	@Test
	void testHighScoreContructor() {
		HighScore sc = new HighScore("Silver");
		assertEquals("Silver", sc.playerName);
	}
	
	@Test
	void testSaveHighScore() {
		HighScore sc = new HighScore("Silver");
		File testScore = new File("testscore.txt");
		assertEquals(testScore, sc.saveHighScore(testScore));
	}

}
