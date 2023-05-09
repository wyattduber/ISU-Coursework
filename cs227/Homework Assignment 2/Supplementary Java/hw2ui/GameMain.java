package hw2ui;

import static hw2.Direction.EAST;
import static hw2.Direction.NORTH;
import static hw2.Direction.SOUTH;
import static hw2.Direction.WEST;
import static hw2.Status.PIT;
import static hw2.Status.WATER;

import java.util.Random;
import java.util.Scanner;

import hw2.CaveExplorer;
import hw2.Direction;
import hw2.Map;

public class GameMain {
	 public static final String[] MAP1 = {
			    "#######",
			    "#XD   #",
			    "###W#P#",
			    "# M W #",
			    "#F#J# #",
			    "#   WK#",
			    "#######",
	 };
	 

	  public static final String[] lARGE= {
			  "#############################",
			  "#                           #",
			  "#                           #",
			  "#                           #",
			  "#                           #",
			  "#     #              JF#    #",
			  "#     #               M#    #",
			  "#     #J             ##     #",
			  "#W     #            #       #",
			  "# W     #D#####    #        #",
			  "#  W    # P   X####         #",
			  "#   W  P########            #",
			  "#WWWWWWWWWWWWWWWWWW         #",
			  "##F               MW        #",
			  "#K#                 W       #",
			  "#J #F                W      #",
			  "#   #                 W     #",
			  "#    #                 W    #",
			  "#     #                FW   #",
			  "#      #                 WFF#",
			  "#       #                   #",
			  "#        #                  #",
			  "#         #M                #",
			  "#          #                #",
			  "#                           #",
			  "#                           #",
			  "#                           #",
			  "#                           #",
			  "#############################",
			};
	public static void main(String[] args) {
		Map m = new Map(MAP1);
		
		// NOTE: set these to true only when DEBUGGING
		boolean printMap = false;
		boolean useSeed = false;
		
		long seed = 10;
		Random generator = new Random();
		if(useSeed)
			generator = new Random(seed);
		
		CaveExplorer player = new CaveExplorer(m, generator);
		printIntro();
		
		Scanner in = new Scanner(System.in);
		String input;
		while(player.isAlive() && !player.hasWon()) {
			printOptions();
			String option = in.next();
			switch(option) {
			case "p":
				player.pickUp();
				break;
			case "r":
				player.rest();
				break;
			case "m":	
				input = in.next();
				Direction direction = str2Direction(input);
				if(direction==null) {
					System.out.println("Invalid direction.");
					break;
				}
				boolean success = player.move(direction);
				printMoveResult(success);
				//player.move();
				break;
			case "j":
				input = in.next();
				direction = str2Direction(input);
				if(direction==null) {
					System.out.println("Invalid direction.");
					break;
				}
				success = player.jump(direction);
				printMoveResult(success);
				//player.jump();
				break;
			case "c":
				String list = player.checkResource();
				System.out.println(list);
				break;
			case "l":
				input = in.next();
				direction = str2Direction(input);
				if(direction==null) {
					System.out.println("Invalid direction.");
					break;
				}
				String view = player.look(direction);
				System.out.println("You saw [" + view + "] in the " + direction);
				break;
			case "e":
				player.eat();
				break;
			default:
				System.out.println("Invalid option. Choose again.\n");
			}
			if(player.getMap().getCellStatus(player.getLocation())==PIT || 
			   player.getMap().getCellStatus(player.getLocation())==WATER && !player.hasJacket())
				System.out.println("You fell into " + player.getMap().getCellStatus(player.getLocation()));
			if(player.getMap().getCellStatus(player.getLocation())==WATER) 
				System.out.println("You lost half of your matches and food items.");
			if(printMap) // For Debugging only
				System.out.println(player.getMap() + "\n");
		}
		if(player.hasWon()) { 
			System.out.println("Congratulations! You have reached the EXIT!");
		} else
			System.out.println("Sorry, you have run out of time or energy. Try again.");
			

	}
	
	private static void printMoveResult(boolean success) {
		if(success)
			System.out.println("Move successfully.");
		else {
			System.out.println("Move failed.");
		}
	}
	
	private static Direction str2Direction(String input) {
		switch(input) {
		case "e":
			return EAST;
		case "w":
			return WEST;
		case "n":
			return NORTH;
		case "s":
			return SOUTH;
		default:
			return null;
		}
	}
	
	/**
	 * Print a welcome message
	 */
	public static void printIntro() {
		System.out.println("Welcome to CaveExplorer!\n");
	}
	
	/**
	 * print options.
	 */
	public static void printOptions() {
		System.out.println("l for look");
		System.out.println("m for move");
		System.out.println("j for jump");
		System.out.println("e for eat");
		System.out.println("c for checking resources");
		System.out.println("r for rest");
		System.out.println("p for pickup");
		System.out.println("look, move, jump MUST be followed by a direction (e)ast, (w)est, " + 
					"(n)orth, or (s)outh. "
				+ "\nFor example, type \"l e\" to look east, \"m s\" to move south, "
				+ "or \"j n\" to jump north.");
		System.out.println("Enter your input: ");
	}

}
