package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import hw3.Life;

public class LifePanel extends JPanel {

	/**
	 * Unique Serialization ID
	 */
	private static final long serialVersionUID = 1L;
	public static final int gridCellSize = 20;
	public static final int minimumGridWidth = 30 * gridCellSize;

	/**
	 * Color for inactive grid squares.
	 */
	public static final Color INACTIVE_COLOR = Color.lightGray;
	/**
	 * Color for active grid squares (inside the simulation).
	 */
	public static final Color ACTIVE_COLOR = Color.BLACK;

	private Life simulation;

	/**
	 * Constructor for LifePanel.
	 * 
	 * @param simulation The Life simulation this panel will display
	 */
	public LifePanel(Life simulation) {
		this.simulation = simulation;
	}

	/**
	 * Move to the next generation and update the display. Call this method rather
	 * than {@link Life#nextGeneration()}.
	 */
	public void nextGeneration() {
		simulation.nextGeneration();
		repaint();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Dimension getPreferredSize() {
		int squareWidth = Math.max(simulation.getRows(), simulation.getColumns());
		Dimension fromContent = new Dimension(gridCellSize * squareWidth + 1, gridCellSize * squareWidth + 1);
		Dimension minimum = new Dimension(minimumGridWidth, minimumGridWidth);

		if (squareWidth < 600) {
			return minimum;
		} else {
			return fromContent;
		}
	}

	private void drawGrid(Graphics g) {
		// Dimensions of this panel in terms of cells
		final int gridWidth = getPreferredSize().width / gridCellSize;
		final int gridHeight = getPreferredSize().height / gridCellSize;

		// Draw a gray grid over the whole thing
		g.setColor(INACTIVE_COLOR);
		for (int row = 0; row < gridWidth; row++) {
			for (int col = 0; col < gridHeight; col++) {
				g.drawRect(gridCellSize * col, gridCellSize * row, gridCellSize, gridCellSize);
			}
		}

		// Display the cells in the center
		g.setColor(ACTIVE_COLOR);

		// How many cells offset to put the automaton in the center
		final int rowOffset = (gridWidth / 2) - (simulation.getRows() / 2);
		final int colOffset = (gridHeight / 2) - (simulation.getColumns() / 2);
		for (int row = 0; row < simulation.getRows(); row++) {
			for (int col = 0; col < simulation.getColumns(); col++) {
				Rectangle gridSquare = new Rectangle(gridCellSize * (col + colOffset), gridCellSize * (row + rowOffset),
						gridCellSize, gridCellSize);

				if (simulation.getCell(row, col).isAlive()) {
					g.fillRect(gridSquare.x, gridSquare.y, gridSquare.width, gridSquare.height);
				} else {
					g.drawRect(gridSquare.x, gridSquare.y, gridSquare.width, gridSquare.height);
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		drawGrid(g);
	}

}
