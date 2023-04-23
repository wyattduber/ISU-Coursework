package edu.iastate.cs228.hw2;


import java.util.Comparator;


/**
 * An implementation of {@link Sorter} that performs merge sort
 * to sort the list.
 * 
 * @author Wyatt Duberstein
 */
public class MergeSorter extends Sorter
{
  @Override
  public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException{

    mergeSortRec(toSort, comp, 0, toSort.length() - 1);

  }

  private void mergeSortRec(WordList list, Comparator<String> comp, int start, int end) {

    if (start >= end) {
      return;
    }

    int mid = (start + end) / 2;
    mergeSortRec(list, comp, start, mid);
    mergeSortRec(list, comp, mid + 1, end);

    merge(comp, list.getArray(), start, mid, end);

  }

  private void merge(Comparator<String> comp, String[] list, int start, int middle, int end) {

    int firstEnd = middle;
    int secondStart = middle + 1;

    int l = start;

    while ((l <= firstEnd) && (secondStart <= end)) {
      if (comp.compare(list[start], list[secondStart]) < 0) {
        start++;
      } else {
        String temp = list[secondStart];
        if (secondStart - start >= 0) System.arraycopy(list, start, list, start + 1, secondStart - start);
        list[start] = temp;
        start++;
        firstEnd++;
        secondStart++;
      }
    }

  }

}
