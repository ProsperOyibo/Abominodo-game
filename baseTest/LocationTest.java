package baseTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import base.Location;
import base.Location.DIRECTION;

class LocationTest {

	@Test
	void testLocationConstructor() {
		Location loc = new Location(2,4);
		assertEquals(2, loc.r);
		assertEquals(4, loc.c);
	}

	@Test
	void testLocationContrustorDIRECTION() {
		Location loc = new Location(5,3);
		assertEquals(5, loc.r);
		assertEquals(3, loc.c);
		loc.d = DIRECTION.HORIZONTAL;
		Location newLoc = new Location(4,7, loc.d);
		assertEquals(4, newLoc.r);
		assertEquals(7, newLoc.c);
		assertEquals(DIRECTION.HORIZONTAL, newLoc.d);
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
