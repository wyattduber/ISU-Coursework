import java.io.IOException;
import java.util.ArrayList;

public class HW3 {

    public static void main(String[] args) throws IOException {
        FrogGame fg = new FrogGame();

        fg.readInput("input.txt"/* args[0] */);

        ArrayList<GamePlan> gps = fg.getGamePlans();

        GamePlan gp;
        ArrayList<int[]> plan;

        if (gps == null) {
            System.out.println("No plans");
        } else {
            for (GamePlan gamePlan : gps) {
                gp = gamePlan;
                System.out.println("**For Goal Location** " + gp.getGoalLoc());
                System.out.println("Number of plans: " + gp.getNumOfPlans());
                System.out.println("One of the plans: ");
                plan = gp.getPlan();

                for (int[] ints : plan) {
                    for (int anInt : ints) {
                        System.out.print(anInt + " ");
                    }
                    System.out.println();
                }
            }
        }
    }

}
