package edu.iastate.cs228.hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class representing an ordering of characters that can be queried to know
 * the position of a given character.
 * 
 * @author Wyatt Duberstein
 */
public class Alphabet {
  /**
   * A lookup table containing characters and their positions.
   * Sorted by the character of each entry.
   */
  private final CharAndPos[] lookup;


  /**
   * Constructs and initializes the ordering to have exactly the ordering of
   * the elements in the given array.
   * 
   * @param ordering
   *   the array containing the characters, in the ordering desired
   * @throws NullPointerException
   *   if {@code ordering} is {@code null}
   */
  public Alphabet(char[] ordering) throws NullPointerException {
    lookup = new CharAndPos[ordering.length];
    for(int i = 0; i < ordering.length; i++) {
      try {
        lookup[i] = new CharAndPos(ordering[i], i);
      } catch (NullPointerException e) {
        e.printStackTrace();
      }
    }

    /* Sort the lookup array by character */
    sortAlphabet(lookup);

  }

  /**
   * Constructs and initializes the ordering by reading from the indicated
   * file. The file is expected to have a single character on each line, and
   * the ordering in the file is the order that will be used.
   * 
   * @param filename
   *   the name of the file to read
   * @throws NullPointerException
   *   if {@code filename} is {@code null}
   * @throws FileNotFoundException
   *   if the file cannot be found
   */
  public Alphabet(String filename) throws NullPointerException, FileNotFoundException {

    File file = new File(filename);
    Scanner scanner = new Scanner(file);

    ArrayList<String> lookup = new ArrayList<>();

    while (scanner.hasNextLine()) {
      lookup.add(scanner.nextLine());
    }

    this.lookup = new CharAndPos[lookup.size()];
    for (int i = 0; i < lookup.size(); i++) {
      this.lookup[i] = new CharAndPos(lookup.get(i).charAt(0), i);
    }

    sortAlphabet(this.lookup);

    scanner.close(); // Close the Scanner once finished
  }


  /**
   * Returns true if and only if the given character is present in the
   * ordering.
   * 
   * @param c
   *   the character to test
   * @return
   *   true if and only if the given character is present in the ordering
   */
  public boolean isValid(char c){
    for (CharAndPos charAndPos : lookup) {
      if (charAndPos.character == c) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the position of the given character in the ordering.
   * Returns a negative value if the given character is not present in the
   * ordering.
   * 
   * @param c
   *   the character of which the position will be determined
   * @return
   *   the position of the given character, or a negative value if the given
   *   character is not present in the ordering
   */
  public int getPosition(char c){

    int index = binarySearch(c);

    /* Check to see if binarySearch returned a -1 */
    if (index == -1) {
      return -1; // char is not in the alphabet
    }

    return lookup[index].position; // chat is within the alphabet, return the index of the ordering.
  }

  /**
   * Returns the index of the entry containing the given character within the
   * lookup table {@link #lookup}.
   * Returns a negative value if the given character does not have an entry in
   * the table.
   * 
   * @param toFind
   *   the character for which to search
   * @return
   *   the index of the entry containing the given character, or a negative
   *   value if the given character does not have an entry in the table
   */
  private int binarySearch(char toFind){
    /*
     * note: for testing, you can perform a simple search through the array
     * instead of a binary search, allowing you to test other components with a
     * working (but slower) implementation
     */

    int first = 0;
    int middle = lookup.length / 2;
    int last = lookup.length;

    while (first <= last) {
      char c = lookup[middle].character;
      if (c < toFind) {
       first = middle + 1;
      } else if (c == toFind) {
        return middle;
      } else {
        last = middle - 1;
      }
      middle = (first + last) / 2;
    }
    return -1;
  }

  /**
   * Helper method to sort the Alphabet into ASCII order
   * I implemented bubble sort here rather than using Arrays.sort()
   * to compare the sorting times,
   * @param lookup
   * the array to sort
   */
  private void sortAlphabet(CharAndPos[] lookup) {
    int n = lookup.length;
    CharAndPos temp;
    for (int i = 0; i < n; i++) {
      for (int j = 1; j < (n - i); j++) {
        if (lookup[j-1].character > lookup[j].character) {
          temp = lookup[j - 1];
          lookup[j - 1] = lookup[j];
          lookup[j] = temp;
        }
      }
    }
  }

  /**
   * A PODT class containing a character and a position.
   * Used as the entry type within {@link Alphabet#lookup lookup}.
   */
  /* already completed */
  private static class CharAndPos{
    /**
     * The character of the entry.
     */
    public char character;

    /**
     * The position of the entry in the ordering.
     */
    public int position;


    /**
     * Constructs and initializes the entry with the given values.
     * 
     * @param character
     *   the character of the entry
     * @param position
     *   the position of the entry
     */
    public CharAndPos(char character, int position) {
      this.character = character;
      this.position = position;
    }


    @Override
    public boolean equals(Object obj) {
      if (null == obj || this.getClass() != obj.getClass())
      {
        return false;
      }

      CharAndPos o = (CharAndPos) obj;

      return this.character == o.character && this.position == o.position;
    }

    @Override
    public int hashCode() {
      return character ^ position;
    }

    @Override
    public String toString() {
      return "{" + character + ", " + position + "}";
    }
  }
}
