package miniassignment1;
import java.util.Random;
import java.util.Scanner;

/**
 * Notes:
 * Use the useDelimiter(",") scanner method to tokenize our input for isGeometric().
 * removeSingles is the only problem which requires nested loops.
 *
 */

public class S2020Mini1 {

    private static int difference;
    private static boolean geo;
    private static String remSingles;
    public static int nextIndex;

    /**
     *
     * @param s
     * @param t
     * @return
     * Returns the number of positions in which the input strings differ.
     * If the strings are different lengths, each extra character in the longer string differs from the shorter string.
     */
    public static int countDifferences(String s, String t) {
        int larger = 0;
        int smaller = 0;
        difference = 0;
        if (s.length() == t.length()) {
            for (int i = 0; i < s.length(); i++) {
                char c1 = s.charAt(i);
                char c2 = t.charAt(i);
                if (c1 != c2) {
                    difference += 1;
                }
            }
        }
        else if (s.length() > t.length()) {
            larger = s.length();
            smaller = t.length();
            for (int i = 0; i < smaller; i++) {
                char c1 = s.charAt(i);
                char c2 = t.charAt(i);
                if (c1 != c2) {
                    difference += 1;
                }
            }
            difference += larger - smaller;
        }
        else if (s.length() < t.length()) {
            larger = t.length();
            smaller = s.length();
            for (int i = 0; i < smaller; i++) {
                char c1 = s.charAt(i);
                char c2 = t.charAt(i);
                if (c1 != c2) {
                    difference += 1;
                }
            }
            difference += larger - smaller;
        }
        return difference;
    }

    /**
     * @param s
     * The given string to analyze.
     * @return
     * Finds the first occurence of the smallest character in the string s and returns it's index.
     */
    public static int findSmallest(String s) {
        char c = s.charAt(0);
        char min = s.charAt(0);
        int index = 0;
        for (int i = 1; i < s.length(); i++) {
            c = s.charAt(i);
            if (c < min) {
                min = c;
                index = i;
            }
        }
        return index;
    }

    /**
     *
     * @param seq
     * The given sequence to analyze.
     * @return
     * Returns true if and only if seq is a geometric sequence;
     * That is, the values in seq change by a constant multiple.
     */
    public static boolean isGeometric(String seq) {
        Scanner s = new Scanner(seq);
        s.useDelimiter(",");
        double first = s.nextInt();
        double second = s.nextInt();
        double num = second / first;
        first = 0;
        second = 0;
        while (s.hasNext()) {
            first = s.nextInt();
            second = s.nextInt();
            if ((second / first) == num) {
                geo = true;
            } else {
                geo = false;
                break;
            }
        }
        return geo;
    }

    /**
     * Returns a new string constructed from the input such that all instances of characters not next to an equivalent character in the input are removed.
     * @param s
     * The Given string to analyze
     * @return remSingles
     */
    public static String removeSingles(String s) {
        remSingles = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i == 0 || i == s.length() - 1) {
                if (i == 0 && (s.charAt(i) == s.charAt(i + 1))) {
                    remSingles += s.charAt(i);
                }
                else if (i == s.length() - 1 && (s.charAt(i) == s.charAt(i - 1))) {
                    remSingles += s.charAt(i);
                }
            }
            else if (c == s.charAt(i - 1) || c == s.charAt(i + 1)) {
                remSingles += s.charAt(i);
            }
        }
        return remSingles;
    }

    public static int sequenceOfLength(Random r, int limit, int length) {
        int seq = 0;
        boolean isDead = false;
        int[] buffer = new int[length];

        while (!isDead) {
            for (int l = 0; l < length; l++) {
                buffer[l] = r.nextInt(limit);
                seq++;
            }

            nested:
            for (int l = length - 1; l > 0; l--) {

                if (Math.abs(buffer[l] - buffer[l - 1]) != 1) {
                    break nested;
                }
                if (l == 1) {
                    isDead = true;
                }
            }
        }


        return seq;
    }

    /**
     * Calculates the square root of d using Newton's Method
     * @param d
     * Given number to take the square root of
     * @return t
     */
    public static double squareRoot(double d) {
        if (d < 0) { return Double.NaN; }
        else {
            double e = 1E-15;
            double t = d / 2;
            while (Math.abs(t - d / t) > e * t) {
                t = (t + (d / t)) / 2;
            }
            return t;
        }
    }

    public static int nextIndexOf(String s, String sub, int start) {
        if (s.isBlank() || sub.isBlank()) { return -1; }
        nextIndex = 0;
        char csub = sub.charAt(0);
        int didMatch = 0;
        boolean isInstanceBroken = false;
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == sub.charAt(0)) {
                isInstanceBroken = false;
                nextIndex = i;
                for (int l = 0, n = i; l < sub.length(); l++, n++) {
                    if (s.charAt(n) != sub.charAt(l)) {
                        isInstanceBroken = true;
                        break;
                    }
                    didMatch += 1;
                }
            }
        }
        if (isInstanceBroken) { nextIndex = -1; }
        if (didMatch == 0) { nextIndex = -1; }
        return nextIndex;
    }

}
