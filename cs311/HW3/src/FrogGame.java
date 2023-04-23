import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.*;

// Cracker Barrel Peg Game
public class FrogGame {

    private int boardSize;
    private ArrayList<GamePlan> gamePlans;
    private ArrayList<Integer> firstPlan;
    private Helper helper;
    private HashMap<Integer, ArrayList<int[]>> goalLocations;

    public FrogGame() {
        boardSize = 0;
        gamePlans = new ArrayList<>();
    }

    public void readInput(String filename) throws IOException {
        File file = new File(filename);
        Scanner sc = new Scanner(file);

        boardSize = Integer.parseInt(sc.nextLine());
        if (boardSize < 1) throw new IOException(boardSize + " is not a valid board size!");
        else if (boardSize == 1) throw new IOException();

        firstPlan = new ArrayList<>();

        int i = 0;
        while (sc.hasNext()) {
            int tmp = sc.nextInt();
            if (tmp < 1 || tmp > (boardSize * boardSize)) throw new IOException(tmp + " is not a valid board space!");
            firstPlan.add(i, tmp);
            i++;
        }
        helper = new Helper(firstPlan, boardSize);
    }

    public ArrayList<GamePlan> getGamePlans() {
        //if (gamePlans.size() > 1) return gamePlans;
        //else return null;

        goalLocations = helper.startAlgorithm();
        //System.out.println(goalLocations);
        //System.out.println();
        //printGoalLocationsMap();

        ArrayList<GamePlan> gps = new ArrayList<>();

        for (ArrayList<int[]> i : goalLocations.values()) {
            GamePlan gp = new GamePlan();
            gp.setPlan(i);
            gp.setNumOfPlans(findNumOfPlans(i));
            gps.add(gp);
        }

        if (gps.isEmpty()) return null;

        return gps;
    }

    public int findNumOfPlans(ArrayList<int[]> i) {
        int numOfPlans = 0;
        for (int[] j : i) {
            if (j.length == firstPlan.size()) numOfPlans++;
        }
        return numOfPlans;
    }

    public ArrayList<int[]> shortenPlan(ArrayList<int[]> i) {
        int counter = 0;
        for (int[] j : i) {
            counter++;
            if (j.length == i.get(0).length && counter != 1) {
                ArrayList<int[]> tmp = new ArrayList<>();
                for (int k = 0; k < counter - 1; k++) {
                    tmp.add(i.get(k));
                }
                return tmp;
            }
        }
        return null;
    }

    public void printGoalLocationsMap() {
        for (int i : goalLocations.keySet()) {
            for (int[] j : goalLocations.get(i)) {
                System.out.println(i + ": " + Arrays.toString(j));
            }
        }
    }

}

/**
 * Initialize Game Board: Done
 * Analyze Game Board: TODO
 *   Find possible moves
 *      Find all nearby spaces of current frog. If not a frog, move onto next index.
 *          Left/Right: TODO
 *          Top/Bottom: TODO
 *          Diagonal: TODO
 *      If nearby space has a frog, index those
 *      Is there an open space on other side of a nearby frog from current index?
 *          If open space is outside of board or space has a frog, then not open & cannot move // Sanity Check
 *   Find All Possible Moves on Start = # of plans: TODO
 *   Work through each plan
 *      Choose first move (probably by lowest number of index of the first moveable frog?)
 *      See above for individual move finding
 *      Scan board and find indices of new frog locations
 *      Add new indices to current plan
 *      Once only one index has a frog, set goalLoc to that index and add to end of plan
 *      Repeat process with next plan
 *
 */
class GamePlan {

    private int numOfPlans;
    private ArrayList<int[]> plan;

    public GamePlan() {
        numOfPlans = 1;
        plan = new ArrayList<>();
    }

    public int getGoalLoc() {
        return plan.get(plan.size() - 1)[0];
    }

    public int getNumOfPlans() {
        return numOfPlans;
    }

    public ArrayList<int[]> getPlan() {
        return plan;
    }

    public void setPlan(ArrayList<int[]> plan) {
        this.plan = plan;
    }

    public void setNumOfPlans(int numOfPlans) {
        this.numOfPlans = numOfPlans;
    }

}

class Helper {

    private int goalLoc;
    private int numOfPlans;
    private ArrayList<int[]> plan;
    private ArrayList<Integer> frogLocations;
    private HashMap<Integer, Integer> gameBoard;
    private ArrayList<Integer> initialPlan;
    private int boardSize;
    private HashMap<Integer, HashMap<Integer, String>> nearbyLocations;
    private HashMap<Integer, HashMap<Integer, String>> nearbyNearbyLocations;
    private HashMap<Integer, HashMap<Integer, String>> possibleMoves;
    private HashMap<Integer, ArrayList<int[]>> goalLocations;

    public Helper(ArrayList<Integer> initialPlan, int boardSize) {
        frogLocations = initialPlan;
        goalLoc = 3;
        goalLocations = new HashMap<>();
        this.initialPlan = initialPlan;

        int[] tmp = new int[boardSize * boardSize];
        for (int i : frogLocations) {
            tmp[i - 1] = 1;
        }

        gameBoard = new HashMap<>();
        for (int i = 0; i < tmp.length; i++) {
            gameBoard.put(i + 1, tmp[i]);
        }

        //printBoard();

        this.boardSize = (int)Math.sqrt(gameBoard.size());

        tmp = new int[frogLocations.size()];
        for (int i = 0; i < frogLocations.size(); i++) {
            tmp[i] = frogLocations.get(i);
        }
        plan = new ArrayList<>();
        plan.add(0, tmp);
        //System.out.println(Arrays.toString(gameBoard));
        //printBoard();

        //startAlgorithm();
    }

    public int findIndexOfNextMove(String relativeLocation, int index, int boardSize) {
        return switch (relativeLocation) {
            case "above" -> index - boardSize;
            case "above left" -> index - boardSize - 1;
            case "above right" -> index - boardSize + 1;
            case "bottom" -> index + boardSize;
            case "bottom left" -> index + boardSize - 1;
            case "bottom right" -> index + boardSize + 1;
            case "left" -> index - 1;
            case "right" -> index + 1;
            default -> -1;
        };
    }

    public boolean checkValidMove(int start, int finish, int boardSize, String direction) {
        if (finish < 0 || finish > Math.pow(boardSize, 2)) return false;
        int rowOfStart = (int)Math.ceil((double)start / boardSize);
        int rowOfFinish = (int)Math.ceil((double)finish / boardSize);
        if (direction.equals("left") || direction.equals("right")) {
            return rowOfStart == rowOfFinish;
        }
        if (direction.equals("above right") || direction.equals("above left") || direction.equals("bottom right") || direction.equals("bottom left")) {
            if (rowOfStart != rowOfFinish + 2 || rowOfStart != rowOfFinish - 2 || rowOfFinish != rowOfStart + 2 || rowOfFinish != rowOfStart - 2) return false;
        }
        return true;
    }

    public HashMap<Integer, String> findNearby(int index, HashMap<Integer, Integer> gameBoard, int boardSize) {

        HashMap<Integer, String> nearby = new HashMap<>();

        boolean b = index % boardSize != 0 && index != Math.pow(boardSize, 2);
        if (index - boardSize > 0) { // Top of board
            if (gameBoard.get(index - boardSize) == 1) { nearby.put(index - boardSize, "above"); }
            if (index % boardSize != 1 && index != 1) { // Top Left of the Index
                if (gameBoard.get(index - boardSize - 1) == 1) { nearby.put(index - boardSize - 1, "above left"); }
            }
            if (b) { // Top Right of the Index
                if (gameBoard.get(index - boardSize + 1) == 1) { nearby.put(index - boardSize + 1, "above right"); }
            }
        }
        if (index + boardSize <= Math.pow(boardSize, 2)) { // Bottom of board
            if (gameBoard.get(index + boardSize) == 1) { nearby.put(index + boardSize, "bottom"); }
            if (index % boardSize != 1 && index != 1) { // Bottom left of the Index
                if (gameBoard.get(index + boardSize - 1) == 1) { nearby.put(index + boardSize - 1, "bottom left"); }
            }
            if (b) { // Bottom right of the Index
                if (gameBoard.get(index + boardSize + 1) == 1) { nearby.put(index + boardSize + 1, "bottom right"); }
            }
        }
        if (index % boardSize != 1 && index != 1) { // Left of board
            if (gameBoard.get(index - 1) == 1) { nearby.put(index - 1, "left"); }
        }
        if (b) { // Right of board
            if (gameBoard.get(index + 1) == 1) { nearby.put(index + 1, "right"); }
        }

        return nearby;
    }

    public HashMap<Integer, ArrayList<int[]>> startAlgorithm() {
        //printBoard();
        doPlan();
        //printGoalLocationsMap();
        //p();
        //printBoard();
        //printPlan();
        //p("Number of plans: " + numOfPlans);
        //p("Goal Location " + goalLoc);
        return goalLocations;
    }

    public void findPossibleMoves() {
        //System.out.println();
        possibleMoves = new HashMap<>();

        // Find the nearby frogs first
        nearbyLocations = new HashMap<>();
        for (int i : gameBoard.keySet()) {
            if (frogLocations.contains(i)) {
                nearbyLocations.put(i, findNearby(i, gameBoard, boardSize));
                /*for (int j : gameBoard.keySet()) {
                    if (nearbyLocations.get(i).containsKey(j)) {
                        continue;
                    }
                    nearbyLocations.get(i).put(j, "empty");
                }*/
                //p(i + ", " + nearbyLocations.get(i));
            }
        }

        //System.out.println();

        // Then find the open spots of the frogs next to them
        nearbyNearbyLocations = new HashMap<>();
        for (int i : nearbyLocations.keySet()) {
            for (int j : nearbyLocations.get(i).keySet()) {
                try { // Remove Duplicates
                    if (nearbyNearbyLocations.containsKey(j)) {
                        continue;
                    }
                } catch (NullPointerException ignored) {}
                nearbyNearbyLocations.put(j, findNearby(j, gameBoard, boardSize));
                //System.out.println(j + ", " + nearbyNearbyLocations.get(j));
            }
        }

        //p();
        for (int i : nearbyLocations.keySet()) {
            HashMap<Integer, String> specific = new HashMap<>();
            for (int j : nearbyLocations.get(i).keySet()) {
                String direction = nearbyLocations.get(i).get(j);
                if (!nearbyNearbyLocations.get(j).containsValue(direction)) {
                    //p(i + ", " + direction);
                    //p(nearbyNearbyLocations.get(j));
                    int tmp = findIndexOfNextMove(direction, j, boardSize);
                    if (checkValidMove(i, tmp, boardSize, direction)) {
                        specific.put(tmp, direction);
                        possibleMoves.put(i, specific);
                    }
                }
            }
        }

        //p("Possible Moves: " + possibleMoves);
    }

    /**
     * Currently doing multiple runs on the game board without stopping to consider a new set of moves
     * Meaning it's doing both moves on the same gameboard, then continuing from there without stopping
     * Your 1:30am self knew what was happening
     */
    public void doPlan() {
        resetGameBoard();
        findPossibleMoves();
        while (possibleMoves.size() > 0) {
            HashMap<Integer, HashMap<Integer, String>> tmp = possibleMoves;
            for (int i : tmp.keySet()) {
                resetGameBoard();
                findPossibleMoves();
                doSteps(i);
                //printPlan();
                //System.out.println();
                if (plan.get(plan.size() - 1).length == 1)  {
                    if (!goalLocations.containsKey(plan.get(plan.size() - 1)[0])) {
                        goalLocations.put(plan.get(plan.size() - 1)[0], (ArrayList<int[]>) plan.clone());
                    } else {
                        goalLocations.get(plan.get(plan.size() - 1)[0]).addAll(plan);
                    }
               }
            }
        }
    }

    public int doSteps(int i) {
        try {
            for (int j = 0; j < possibleMoves.get(i).size(); j++) {
                //printBoard();
                //p();
                gameBoard.replace(i, 0);
                gameBoard.replace((Integer) possibleMoves.get(i).keySet().toArray()[j], 1);
                gameBoard.replace(findIndexOfNextMove((String) possibleMoves.get(i).values().toArray()[j], i, boardSize), 0);
                ArrayList<Integer> tmp = new ArrayList<>();
                for (int k : gameBoard.keySet()) {
                    if (gameBoard.get(k) == 1) {
                        tmp.add(k);
                    }
                }
                if(!plan.contains(tmp.stream().mapToInt(h -> h).toArray())) plan.add(tmp.stream().mapToInt(h -> h).toArray());
                frogLocations = tmp;
                //printPlan();
                findPossibleMoves();
                try {
                    int test = doSteps((Integer) possibleMoves.keySet().toArray()[0]);
                    //if (test == goalLoc) goalLocations.put(test, plan);
                } catch (ArrayIndexOutOfBoundsException e) {
                    for (int k : gameBoard.keySet()) {
                        //if (gameBoard.get(k) == goalLoc) goalLocations.put(gameBoard.get(k), plan);
                        break;
                    }
                }
            }
        } catch (NullPointerException ignored) {}
        return 0;
    }

    public void resetGameBoard() {
        frogLocations = new ArrayList<>();
        for (int i : gameBoard.keySet()) {
            gameBoard.replace(i, 0);
        }
        for (int i : plan.get(0)) {
            gameBoard.replace(i, 1);
            frogLocations.add(i);
        }

        plan = new ArrayList<>();
        plan.add(initialPlan.stream().mapToInt(h -> h).toArray());
    }

    public ArrayList<int[]> getGoalPlans(int i) {
        return goalLocations.get(i);
    }



    public void printPlan() {
        for (int[] i : plan) {
            p(Arrays.toString(i));
        }
    }

    public void printGoalLocationsMap() {
        for (int i : goalLocations.keySet()) {
            for (int[] j : goalLocations.get(i)) {
                p(i + ": " + Arrays.toString(j));
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < gameBoard.size(); i += Math.sqrt(gameBoard.size())) {
            for (int k = 0; k < Math.sqrt(gameBoard.size()); k++) {
                System.out.print(gameBoard.get(i + k + 1) + " ");
            }
            System.out.println();
        }
    }

    public void p(Object o) {
        System.out.println(o);
    }

    public void p() {
        System.out.println();
    }

}
