package hw2ui;

import java.util.Random;

import hw2.CaveExplorer;
import hw2.Direction;
import hw2.Location;
import hw2.Map;

public class GameTest {
		 public static final String[] MAP0 = {
				    "######",
				    "#XD  #",
				    "###WP#",
				    "#F#J #",
				    "#  WK#",
				    "######",
		 };
		 public static void main(String[] args) {
				Map m = new Map(MAP0);
				CaveExplorer player = new CaveExplorer(m, new Location(4,1));
				System.out.println(player.getMap());
				
				String view = player.look(Direction.EAST);
				System.out.println("expected: OPEN, actual: " + view);
				int clock = player.getClock();
				System.out.println("expected: 99, actual: " + clock);
				int energy = player.getEnergy();
				System.out.println("expected: 99, actual: " + energy);
				
				boolean success = player.move(Direction.EAST);
				System.out.println("expected: true, actual: " + success);
				Location location = player.getLocation();
				System.out.println("expected: [4, 2], actual: " + location);
				view = player.look(Direction.WEST);
				System.out.println("expected: MARK, actual: " + view); // The cell to the west is MARKed
				 
		 }
			 

}
