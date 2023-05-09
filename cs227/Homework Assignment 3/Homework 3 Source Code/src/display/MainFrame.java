package display;

import java.awt.BorderLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * A window that displays a GOL simulation.
 * 
 * @author Ben Steenhoek
 *
 */
public class MainFrame extends JFrame {

	/**
	 * Unique Serialization ID
	 */
	private static final long serialVersionUID = -746668192754492357L;

	/**
	 * Start a GOL window.
	 * 
	 * @param lifePanel    The LifePanel this window displays.
	 * @param controlPanel ControlPanel for the simulation.
	 */
	public MainFrame(LifePanel lifePanel, ControlPanel controlPanel) {
		URL iconUrl = getClass().getResource("window_icon.png");
		if (iconUrl != null) {
			setIconImage(new ImageIcon(iconUrl).getImage());
		}
		setTitle("Homework 3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(controlPanel, BorderLayout.NORTH);
		add(lifePanel, BorderLayout.SOUTH);
		pack();

		setVisible(true);
	}
}
