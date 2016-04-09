package in.ednit.funblox.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.ednit.funblox.model.Board;

public class BoardTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardTest.class);

	@Test
	public void testNoArgConstructor() {
		
		LOGGER.info("Testing default constructor...");
		
		try {
			Board b = new Board();
			int totalCells = b.getTotalCells();
			assertEquals(200, totalCells);
		} catch (InvalidBoardDimensionException e) {
			fail("no arg constructor failed");
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testInvalidWidth() {
		
		LOGGER.info("Testing constructor by setting width less than 1");
		
		try {
			Board b = new Board();
			b.setDimensions(0, 10);
			fail("Board width cannot be less than 1");
		} catch (InvalidBoardDimensionException e) {
			assertTrue(e.getMessage().equals("width cannot be less than 1"));
		}
	}

	@Test
	public void testInvalidHeight() {
		
		LOGGER.info("Testing constructor by setting height less than 1");
		
		try {
			Board b = new Board();
			b.setDimensions(10, 0);
			fail("Board width cannot be less than 1");
		} catch (InvalidBoardDimensionException e) {
			assertTrue(e.getMessage().equals("height cannot be less than 1"));
		}
	}
	
	@Test
	public void testParameterizedConstructor() throws InvalidBoardDimensionException {
		
		LOGGER.info("Testing parameterized constructor with valid values");
		
		try {
			Board b = new Board(5, 6);
			int totalCells = b.getTotalCells();
			assertEquals(5 * 6, totalCells);
			
			for (int x = 1; x <= 5; x++) {
				for (int y = 1; y <= 6; y++) {
					assertTrue(b.isCellEmpty(x, y));
				}
			}
			
		} catch (InvalidCellIndexException e) {
			fail("invalid cell index excelption occured for valid index " + e.getMessage());
		}
	}
	
	@Test
	public void testCellIndexYOutOfHeight() throws InvalidBoardDimensionException {
		
		LOGGER.info("Testing cell index Y with Board size 1x1");
		
		Board b = new Board(1, 1);
		
		LOGGER.info("Testing with Y = 0");
		
		try {
			b.getCell(1, 0);
			fail("invalid cell index was passed but exception was not thrown");
		} catch (InvalidCellIndexException e) {
			assertTrue(e.getMessage().equals("invalid cell index (1, 0)"));
		}
		
		LOGGER.info("Testing with Y = 2");
		
		try {
			b.getCell(1, 2);
			fail("invalid cell index was passed but exception was not thrown");
		} catch (InvalidCellIndexException e) {
			assertTrue(e.getMessage().equals("invalid cell index (1, 2)"));
		}
	}

	@Test
	public void testCellIndexXOutOfWidth() throws InvalidBoardDimensionException {
		
		LOGGER.info("Testing cell index X with Board size 1x1");
		
		Board b = new Board(1, 1);
		
		LOGGER.info("Testing with X = 0");
		
		try {
			b.getCell(0, 1);
			fail("invalid cell index was passed but exception was not thrown");
		} catch (InvalidCellIndexException e) {
			assertTrue(e.getMessage().equals("invalid cell index (0, 1)"));
		}
		
		LOGGER.info("Testing with X = 2");
		
		try {
			b.getCell(2, 1);
			fail("invalid cell index was passed but exception was not thrown");
		} catch (InvalidCellIndexException e) {
			assertTrue(e.getMessage().equals("invalid cell index (2, 1)"));
		}
	}
	
	@Test
	public void testCollapseNoRows() throws InvalidBoardDimensionException, InvalidCellIndexException {
		
		LOGGER.info("Testing when bottom most row is filled");
		Board b1 = new Board(2, 2);
		int collapseAnyCompletedRows = b1.collapseAnyCompletedRows();
		assertEquals(0, collapseAnyCompletedRows);
	}
	
	@Test
	public void testCollapseBottomMostRow() throws InvalidCellIndexException, InvalidBoardDimensionException {
		
		LOGGER.info("Testing when bottom most row is filled");
		Board board = new Board(2, 2);
		board.setCell(1, 1, Cell.SHAPE_I);
		board.setCell(2, 1, Cell.SHAPE_J);
		assertEquals(1, board.collapseAnyCompletedRows());
	}
	
	@Test
	public void testCollapseTopMostRow() throws InvalidCellIndexException, InvalidBoardDimensionException {
		
		LOGGER.info("Testing when top most row is filled");
		Board board = new Board(2, 2);
		board.setCell(1, 1, Cell.SHAPE_I);
		board.setCell(2, 1, Cell.EMPTY);
		board.setCell(1, 2, Cell.SHAPE_I);
		board.setCell(2, 2, Cell.SHAPE_J);
		assertEquals("Top most row should not be checked during collapse test", 0, board.collapseAnyCompletedRows());
	}
		
	@Test
	public void testCollapseBottom2ndRow() throws InvalidCellIndexException, InvalidBoardDimensionException {
		
		LOGGER.info("Testing when 2nd bottom row is filled");
		Board board = new Board(2, 3);
		board.setCell(1, 1, Cell.SHAPE_I);
		board.setCell(2, 1, Cell.EMPTY);
		board.setCell(1, 2, Cell.SHAPE_I);
		board.setCell(2, 2, Cell.SHAPE_J);
		assertEquals(1, board.collapseAnyCompletedRows());
	}
		
	@Test
	public void testCollapseBottom2Rows() throws InvalidCellIndexException, InvalidBoardDimensionException {
		LOGGER.info("Testing when 2 rows are filled");
		Board board = new Board(2, 3);
		board.setCell(1, 1, Cell.SHAPE_I);
		board.setCell(2, 1, Cell.SHAPE_O);
		board.setCell(1, 2, Cell.SHAPE_I);
		board.setCell(2, 2, Cell.SHAPE_J);
		assertEquals(2, board.collapseAnyCompletedRows());
		
	}
	
	@Test
	public void testSetRandomCells() throws InvalidBoardDimensionException, InvalidCellIndexException {
		Board b = new Board();
		b.setRandomCells();
		int width = b.getWidth();
		int height = b.getHeight();
		for (int y = 1; y <= height; y++) 
			for (int x = 1; x <= width; x++)
				assertTrue(" OISZLJT".contains(b.getCell(x, y).toString()));
	}
}
