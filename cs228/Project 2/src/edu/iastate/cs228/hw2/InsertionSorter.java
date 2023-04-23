package edu.iastate.cs228.hw2;


import java.util.Comparator;


/**
 * An implementation of {@link Sorter} that performs insertion sort
 * to sort the list.
 * 
 * @author Wyatt Duberstein
 */
public class InsertionSorter extends Sorter
{
  @Override
  public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException {
    int n = toSort.length();

    for (int j = 1; j < n; j++) {
      String key = toSort.get(j);
      int i = j - 1;

      while (i > -1 && comp.compare(toSort.get(i), key) > 0) {
        toSort.set(i + 1, toSort.get(i));
        i--;
      }
      toSort.set(i + 1, key);
    }

  }
}
