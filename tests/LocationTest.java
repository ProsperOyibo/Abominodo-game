package tests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import base.Location;
import base.Location.DIRECTION;

class LocationTest {

	@Test
	void testLocationConstructor1() {
		Location loc = new Location(2,4);
		assertEquals(2, loc.r);
		assertEquals(4, loc.c);
	}
	
	@Test
	void testLocationConstructor2() {
		Location loc = new Location(3,5);
		assertEquals(3, loc.r);
		assertEquals(5, loc.c);
	}

	@Test
	void testLocationContrustorDIRECTION1() {
		Location loc = new Location(5,3);
		loc.d = DIRECTION.HORIZONTAL;
		Location newLoc = new Location(4,7, loc.d);
		assertEquals(4, newLoc.r);
		assertEquals(7, newLoc.c);
		assertEquals(DIRECTION.HORIZONTAL, newLoc.d);
	}
	
	@Test
	void testLocationContrustorDIRECTION2() {
		Location loc = new Location(6,1);
		loc.d = DIRECTION.VERTICAL;
		Location newLoc = new Location(2,5, loc.d);
		assertEquals(2, newLoc.r);
		assertEquals(5, newLoc.c);
		assertEquals(DIRECTION.VERTICAL, newLoc.d);
	}

	@Test
	void testToStringWithTmp() {
		Location loc = new Location(3,6);
		assertEquals(3, loc.r);
		assertEquals(6, loc.c);
		loc.toString();
		assertEquals(3, loc.r);
		assertEquals(7, loc.tmp);
	}
	

}
