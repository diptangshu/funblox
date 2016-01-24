package in.ednit.funblox.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Board {

	private static final Logger LOGGER = LoggerFactory.getLogger(Board.class);
	private static final int DEFAULT_HEIGHT = 20;
	private static final int DEFAULT_WIDTH = 10;
	
	private int width;
	private int height;
	private int pieceSpawnX;
	private int pieceSpawnY;
	private List<Cell> cells;
	
	
	public Board() throws InvalidBoardDimensionException {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	public Board(int width, int height) throws InvalidBoardDimensionException {
		setDimensions(width, height);
	}
	
	public Board(final Board other) {
		
		LOGGER.debug("Copying from another board");
		
		if (!other.isValid()) 
			throw new IllegalArgumentException("invalid source board");
		
		if (!isValid() || width != other.width || height != other.height) {
			try {
				setDimensions(other.width, other.height);
			} catch (InvalidBoardDimensionException e) {
				LOGGER.error(e.getMessage());
			}
		}
		
		Collections.copy(cells, other.cells);
		
	}

	public void setDimensions(int width, int height) throws InvalidBoardDimensionException {
		
		LOGGER.debug("Setting dimensions to {}x{}", width, height);
		
		if (width < 1) throw new InvalidBoardDimensionException("width cannot be less than 1");
		if (height < 1) throw new InvalidBoardDimensionException("height cannot be less than 1");
		
		
		this.width = width;
		this.height = height;
		this.pieceSpawnX = 1 + Math.floorDiv(width, 2);
		this.pieceSpawnY = height;

		clearCells();
	}
	
	public boolean isValid() {
		return width > 0 && height > 0 && cells != null && !cells.isEmpty();
	}
	
	public void validate() throws RuntimeException {
		if (!isValid()) throw new RuntimeException("invalid board");
	}
	
	public int getTotalCells() {
		return width * height;
	}
	
	public void clearCells() {
		
		LOGGER.debug("Clearing cells");
		
		cells = new ArrayList<Cell>(getTotalCells());
		for (int i = 0; i < getTotalCells(); i++) cells.add(Cell.EMPTY);
	}
	
	private int getCellIndex(int x, int y) throws InvalidCellIndexException {
		
		if (x < 1 || x > width) throw new InvalidCellIndexException(x, y);
		if (y < 1 || y > height) throw new InvalidCellIndexException(x, y);
		
		validate();
		
		return width * (y - 1) + (x - 1);
	}
	
	public Cell getCell(int x, int y) throws InvalidCellIndexException {
		
		validate();
		return cells.get(getCellIndex(x, y));
	}
	
	public void setCell(int x, int y, final Cell cell) throws InvalidCellIndexException {
		
		validate();
		cells.set(getCellIndex(x, y), cell);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int collapseAnyCompletedRows() throws InvalidCellIndexException {
		
		LOGGER.debug("Collapsing rows from bottom that are completely filled");
		
		validate();
		
		int totalRowsCollapsed = 0;
		
		for (int y = 1; y < height; y++) {
			
			// check if the row is full
			boolean rowIsFull = true;
			for (int x = 1; x <= width && rowIsFull; x++) {
				rowIsFull = !isCellEmpty(x, y);
			}
			
			// if row is complete update statistic and collapse the pile down
			if (rowIsFull) {
				totalRowsCollapsed++;
				
				for (int copyY = y + 1; copyY <= height; copyY++) {
					for (int copyX = 1; copyX <= width; copyX++) {
						Cell copiedCell = getCell(copyX, copyY);
						setCell(copyX, copyY - 1, copiedCell);
					}
				}
				
				// clear top row
				for (int copyX = 1; copyX <= width; copyX++) {
					setCell(copyX, height, Cell.EMPTY);
				}
				
				// Force the same row to be checked again
				y--;
			}
		}
		
		return totalRowsCollapsed;
	}

	public boolean isCellEmpty(int x, int y) throws InvalidCellIndexException {
		return getCell(x, y).isEmpty();
	}
	
	public void setRandomCells() {
		
		LOGGER.debug("Setting random cells in board");
		
		validate();
		int totalCells = getTotalCells();
		Cell[] cells = { 
				Cell.EMPTY, 
				Cell.SHAPE_O, 
				Cell.SHAPE_I,
				Cell.SHAPE_S,
				Cell.SHAPE_Z,
				Cell.SHAPE_L,
				Cell.SHAPE_J,
				Cell.SHAPE_T
			};
		
		Random random = new Random();
		for (int i = 0; i < totalCells; i++) {
			this.cells.set(i, cells[random.nextInt(8)]);
		}
	}
}
