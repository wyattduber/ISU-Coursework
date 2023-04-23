package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * MsgTree.java
 *
 * A class that can take a file, construct a tree with a special encoding used to decode messages
 * then decodes them and shows the message and outputs some extra statistics.
 *
 * @author Wyatt Duberstein
 */

public class MsgTree {

    public char payloadChar;
    public MsgTree left;
    public MsgTree right;
    private static String decodedMessage = "";

    // Static char idx to the tree string for recursive solution
    private static int staticCharIdx = 0;

    /**
     * Constructor that recursively constructs a MsgTree
     * with MsgTree Nodes within it that hold 1 payloadChar as their node data
     * @param encodingString
     */
    public MsgTree(String encodingString) {
        char temp = encodingString.charAt(staticCharIdx);
        if (temp != '^') { // If the char at index is not a carat, we have a leaf node, so make a new node.
            this.payloadChar = temp;
            staticCharIdx++;
        } else {
            staticCharIdx++;
            /* Recursion in PreOrder Traversal */
            left = new MsgTree(encodingString);
            right = new MsgTree(encodingString);
        }
    }

    /**
     * Constructor for a single MsgTree node within the tree that
     * holds 1 payload char
     * @param payloadChar
     */
    public MsgTree(char payloadChar) {
        this.payloadChar = payloadChar;
    }


    // Method to print characters and their binary codes
    public static void printCodes(MsgTree root, String code) {
        if (root == null) { // Check if the root is null
            return;
        }

        if (root.left == null && root.right == null) { // Base case for when we're at a leaf node
            if (root.payloadChar == '\n') { // Special case for the newline character
                System.out.println("\\n" + "           " + code);
            }else {
                System.out.println(root.payloadChar + "            " + code); // Case for every other character
            }
        }

        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    /**
     * Decoder method to take the tree of codes and the message
     * then decode the message and output what it's supposed to say.
     * @param codes
     * @param msg
     */
    public void decode(MsgTree codes, String msg) {

        if (codes == null) {
            return;
        }

        String subMsg = msg;
        MsgTree tmp = codes;
        decodedMessage = "";

        while (subMsg.length() > 0) {
            if (subMsg.charAt(0) == '0') {
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }

            if (tmp.right == null && tmp.left == null) {
                decodedMessage += tmp.payloadChar;
                System.out.print(tmp.payloadChar);
                tmp = codes;
            }
            subMsg = subMsg.substring(1);
        }
    }

    /**
     * Main method, which prompts the user for the filename, then gets the encoding and
     * the code from the file, prints the codes, decodes the message
     * and prints it, then finds statistics based on the data.
     * @param args
     */
    public static void main(String[] args) {

        Scanner irrelevant = new Scanner(System.in);
        Scanner anotherScanner = null;
        Scanner sc = null;
        int index = 0;
        String encoding;
        String code;

        System.out.println("Please enter filename to decode:");
        File file = new File(irrelevant.next());
        try {
            sc = new Scanner(file);
            anotherScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(anotherScanner.hasNextLine()) {
            index++;
            anotherScanner.nextLine();
        }

        encoding = sc.nextLine();
        if (index == 3) {
            encoding += '\n';
            encoding += sc.nextLine();
        }

        code = sc.nextLine();

        MsgTree tree = new MsgTree(encoding);

        // Format the printCode method nicely
        System.out.println();
        System.out.println("character    code");
        System.out.println("-------------------------");
        printCodes(tree, "");

        System.out.println();
        System.out.println("MESSAGE:");
        tree.decode(tree, code);

        // Statistic Calculations
        double space = (1 - ((double) code.length() / (decodedMessage.length() * 16))) * 100;

        System.out.println("\n"); // Ignore the sheer insanity of this line, I wanted things to look neat
        System.out.println("STATISTICS:");
        System.out.println("Avg bits/char:" + "\t " + (double) code.length() / decodedMessage.length());
        System.out.println("Total characters:" + "\t " + decodedMessage.length());
        System.out.println("Space savings:" + "\t " + space + "%");

    }

}
