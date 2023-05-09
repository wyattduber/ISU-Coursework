package display;

import javax.swing.SwingUtilities;

import hw3.Life;

/**
 * Utility methods for a GUI for Conway's Game of Life.
 * 
 * @author Ben Steenhoek
 *
 */
public class LifeGui {
	/**
	 * Launch with specified rules.
	 * 
	 * @param grid    A grid defining the initial state of the simulation.
	 * @param born    Rules defining when cells should be born.
	 * @param survive Rules defining when cells should survive.
	 */
	public static void launchGui(String[] grid, int[] born, int[] survive) {
		Life simulation = new Life(grid, born, survive);
		LifePanel lifePanel = new LifePanel(simulation);
		ControlPanel controlPanel = new ControlPanel(lifePanel);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame(lifePanel, controlPanel);
			}
		});
	}

	/**
	 * Launch with specified Life instance.
	 * 
	 * @param simulation An instance of Life.
	 */
	public static void launchGui(Life simulation) {
		LifePanel lifePanel = new LifePanel(simulation);
		ControlPanel controlPanel = new ControlPanel(lifePanel);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame(lifePanel, controlPanel);
			}
		});
	}
}
