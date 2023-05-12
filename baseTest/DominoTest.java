package baseTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import base.Domino;

class DominoTest {

	@Test
	void testDominoConstructor() {
		Domino d = new Domino(5,2);
		assertEquals(5,d.high);
		assertEquals(2,d.low);
	}

	@Test
	void testPlace() {
		Domino d = new Domino(6,3);
		d.place(2, 1, 3, 2);
		assertEquals(2,d.hx);
		assertEquals(1,d.hy);
		assertEquals(3,d.lx);
		assertEquals(2,d.ly);
		assertEquals(true, d.placed);
	}
	
	@Test
	void testInverted() {
		Domino d = new Domino(6,3);
		d.place(2, 1, 3, 1);
		d.invert();
		assertEquals(true, d.ishl());
		
	}
	

}
