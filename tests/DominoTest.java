package tests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import base.Domino;

class DominoTest {

	@Test
	void testDominoConstructor1() {
		Domino d = new Domino(5,2);
		assertEquals(5,d.high);
		assertEquals(2,d.low);
	}
	
	@Test
	void testDominoConstructor2() {
		Domino d = new Domino(4,3);
		assertEquals(4,d.high);
		assertEquals(3,d.low);
	}

	@Test
	void testPlace1() {
		Domino d = new Domino(6,3);
		d.place(2, 1, 3, 2);
		assertEquals(2,d.hx);
		assertEquals(1,d.hy);
		assertEquals(3,d.lx);
		assertEquals(2,d.ly);
		assertEquals(true, d.placed);
	}
	
	@Test
	void testPlace2() {
		Domino d = new Domino(1,1);
		d.place(6, 3, 5, 3);
		assertEquals(6,d.hx);
		assertEquals(3,d.hy);
		assertEquals(5,d.lx);
		assertEquals(3,d.ly);
		assertEquals(true, d.placed);
	}
	
	@Test
	void testInverted1() {
		Domino d = new Domino(6,3);
		d.place(2, 1, 3, 1);
		d.invert();
		assertEquals(true, d.ishl());
		
	}
	
	@Test
	void testInverted2() {
		Domino d = new Domino(5,1);
		d.place(4, 1, 1, 1);
		d.invert();
		assertEquals(true, d.ishl());
		
	}
	

}
