import java.io.File;
import java.io.IOException;
import java.util.*;

public class FrogGame {
    int gridSize;
    HashSet<Integer> gridMap;

    public FrogGame() {
        gridSize = 0;
        gridMap = new HashSet<>();
    }

    public void readInput(String FileName) throws IOException {

        File fileName = new File(FileName);
        Scanner fileReader = new Scanner(fileName);

        gridSize = Integer.parseInt(fileReader.nextLine());
        String[] givenFrogLocations = fileReader.nextLine().split("\\s");

        fileReader.close();

        // Creates starting grid by locating indexes of frogs and adding to hashset
        for (String location : givenFrogLocations) {
            gridMap.add(Integer.parseInt(location));
        }
    }

    public ArrayList<GamePlan> getGamePlans() {
        Helper helper = new Helper();
        // Creates tree based on permutations from original grid
        Node root = new Node(gridMap, null);
        ArrayList<Node> parents = new ArrayList<>();
        ArrayList<Node> nextParents = new ArrayList<>();
        ArrayList<Node> wantedLeafs = new ArrayList<>();
        // Goes row by row of permutations from root
        parents.add(root);
        while (!parents.isEmpty()) {
            for (Node parent : parents) {
                ArrayList<HashSet<Integer>> permutations = helper.getPermutations(gridSize, parent.getMap());
                if (permutations != null) {
                    for (HashSet<Integer> permutation : permutations) {
                        Node currentNode = new Node(permutation, parent);
                        // Store winning leafs for later
                        if (currentNode.getMap().size() == 1) {
                            wantedLeafs.add(currentNode);
                        }
                        nextParents.add(parent.addChild(currentNode));
                    }
                }
            }
            parents = nextParents;
            nextParents = new ArrayList<>();
        }

        // System.out.println(root.toString()); /* Uncomment to see tree visual */

        // Create GamePlan objects for final output, keep track of duplicate win location with hash
        ArrayList<GamePlan> gamePlans = new ArrayList<>();
        HashMap<Integer, GamePlan> goalLocations = new HashMap<>();
        for (Node leaf : wantedLeafs) {
            GamePlan gp = new GamePlan();
            if (goalLocations.get(leaf.getMap().iterator().next()) == null) {
                gamePlans.add(helper.convertBranch(leaf, gp));
                goalLocations.put(leaf.getMap().iterator().next(), gp);
            } else {
                goalLocations.get(leaf.getMap().iterator().next()).numOfPlans += 1;
            }
        }

        if (gamePlans.isEmpty()) { return null; }
        return gamePlans;
    }
}

class GamePlan {
    int goalLoc;
    int numOfPlans;
    ArrayList<int[]> plan;

    public GamePlan() {
        goalLoc = 0;
        numOfPlans = 1;
        plan = new ArrayList<>();
    }

    public int getgoalLoc() {
        return plan.get(plan.size() - 1)[0];
    }

    public int getnumOfPlans() {
        return numOfPlans;
    }

    public ArrayList<int[]> getPlan() {
        return plan;
    }
}

class Helper {
    // Returns pairs of possible moves that can eliminate a frog
    public HashMap<String, int[]> getPaths(int target, int gridSize, HashSet<Integer> gridMap) {
        HashMap<String, int[]> paths = new HashMap<>();
        int top = -1, bottom = -1, left = -1, right = -1, topLeft = -1, topRight = -1, bottomLeft = -1, bottomRight = -1;

        if (target > gridSize) { // If not row 1, look up
            top = gridMap.contains(target - gridSize) ? 1 : 0;

            if (target % gridSize != 1) { // If not column 1, look left
                topLeft = gridMap.contains(target - gridSize - 1) ? 1 : 0;
            }
            if (target % gridSize != 0) { // If not colum gridSize, look right
                topRight = gridMap.contains(target - gridSize + 1) ? 1 : 0;
            }
        }
        if (target <= Math.pow(gridSize, 2) - gridSize) { // If not row gridSize, look down
            bottom = gridMap.contains(target + gridSize) ? 1 : 0;

            if (target % gridSize != 1) { // If not column 1, look left
                bottomLeft = gridMap.contains(target + gridSize - 1) ? 1 : 0;
            }
            if (target % gridSize != 0) { // If not colum gridSize, look right
                bottomRight = gridMap.contains(target + gridSize + 1) ? 1 : 0;
            }
        }
        if (target % gridSize != 1) { // If not column 1, look left
            left = gridMap.contains(target - 1) ? 1 : 0;
        }
        if (target % gridSize != 0) { // If not colum gridSize, look right
            right = gridMap.contains(target + 1) ? 1 : 0;
        }

        // Adds to output indexes of move, as well as original target index
        if (top != bottom && top != -1 && bottom != -1) {
            paths.put("Top:Bottom", new int[]{target - gridSize, target + gridSize, target});
        }
        if (left != right && left != -1 && right != -1) {
            paths.put("Left:Right", new int[]{target - 1, target + 1, target});
        }
        if (topLeft != bottomRight && topLeft != -1 && bottomRight != -1) {
            paths.put("TopLeft:BottomRight", new int[]{target - gridSize - 1, target + gridSize + 1, target});
        }
        if (topRight != bottomLeft && topRight != -1 && bottomLeft != -1) {
            paths.put("TopRight:BottomLeft", new int[]{target - gridSize + 1, target + gridSize - 1, target});
        }

        return paths;
    }

    // Discovers permutations from a given grid
    public ArrayList<HashSet<Integer>> getPermutations(int gridSize, HashSet<Integer> gridMap) {
        ArrayList<HashSet<Integer>> permutations = new ArrayList<>();

        // Checks possible moves for each frog on passed grid
        for (Integer mapIndex : gridMap) {
            HashMap<String, int[]> pathsAtPoint = getPaths(mapIndex, gridSize, gridMap);

            if (pathsAtPoint != null) {
                // Ensures all permutation are accounted for even if a frog has two possible moves to get eliminated
                for (Map.Entry<String, int[]> pathSet : pathsAtPoint.entrySet()) {
                    HashSet<Integer> permutation = new HashSet<>(gridMap);
                    permutation.remove(pathSet.getValue()[2]);

                    if (permutation.contains(pathSet.getValue()[0])) {
                        permutation.remove(pathSet.getValue()[0]);
                        permutation.add(pathSet.getValue()[1]);
                    } else if (permutation.contains(pathSet.getValue()[1])) {
                        permutation.remove(pathSet.getValue()[1]);
                        permutation.add(pathSet.getValue()[0]);
                    }
                    permutations.add(permutation);
                }
            }
        }
        if (permutations.isEmpty()) {
            return null;
        }
        return permutations;
    }

    // Does a DFS, converting hashset to int[] from winning leaf to root
    public GamePlan convertBranch(Node leaf, GamePlan gp) {
        gp.plan.add(0, leaf.getMap().stream().mapToInt(Number::intValue).toArray());
        if (leaf.getParent() != null) {
            convertBranch(leaf.getParent(), gp);
        }
        return gp;
    }
}

class Node {
    private List<Node> children;
    private Node parent;
    private HashSet<Integer> value;

    public Node(HashSet<Integer> value, Node parent) {
        this.children = new ArrayList<>();
        this.parent = parent;
        this.value = value;
    }

    public Node addChild(Node child) {
        children.add(child);
        return child;
    }

    public Node getParent() {
        return this.parent;
    }

    public HashSet<Integer> getMap() {
        return this.value;
    }

    // The below two functions are use for visualizing purposes only
    // Sourced from https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java
    public String toString() {
        StringBuilder buffer = new StringBuilder(50);
        print(buffer, "", "");
        return buffer.toString();
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(this.getMap());
        buffer.append('\n');
        for (Iterator<Node> it = children.iterator(); it.hasNext();) {
            Node next = it.next();
            if (it.hasNext()) {
                next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
            } else {
                next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }
    }
}