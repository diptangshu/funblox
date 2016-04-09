package in.ednit.funblox.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CellTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CellTest.class);

	@Test
	public void testIsEmpty() {
		assertTrue(Cell.EMPTY.isEmpty());
	}
	
	@Test
	public void testToString() {
		LOGGER.info("Testing toString()");
		assertEquals(" ", Cell.EMPTY.toString());
		assertEquals("O", Cell.SHAPE_O.toString());
		assertEquals("I", Cell.SHAPE_I.toString());
		assertEquals("S", Cell.SHAPE_S.toString());
		assertEquals("Z", Cell.SHAPE_Z.toString());
		assertEquals("L", Cell.SHAPE_L.toString());
		assertEquals("J", Cell.SHAPE_J.toString());
		assertEquals("T", Cell.SHAPE_T.toString());
	}

}
